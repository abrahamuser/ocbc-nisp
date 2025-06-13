package javapns;

import java.util.*;

import javapns.communication.exceptions.*;
import javapns.devices.*;
import javapns.devices.exceptions.*;
import javapns.devices.implementations.basic.*;
import javapns.feedback.*;
import javapns.notification.*;
import javapns.notification.transmission.*;

/**
 * <p>Main class for easily interacting with the Apple Push Notification System</p>
 * 
 * <p>This is the best starting point for pushing simple or custom notifications,
 * or for contacting the Feedback Service to cleanup your list of devices.</p>
 * 
 * <p>The <b>JavaPNS</b> library also includes more advanced options such as
 * multithreaded transmission, special payloads, and more.
 * See the library's documentation at <a href="http://code.google.com/p/javapns/">http://code.google.com/p/javapns/</a>
 * for more information.</p>
 * 
 * @author Sylvain Pedneault
 * @see NotificationThreads
 */
public class Push {

	private Push() {

	}
	
	/**
	 * Push a preformatted payload to a list of devices.
	 * 
	 * @param payload a simple or complex payload to push.
	 * @param keystore a keystore containing your private key and the certificate signed by Apple ({@link java.io.File}, {@link java.io.InputStream}, byte[], {@link java.security.KeyStore} or {@link java.lang.String} for a file path)
	 * @param password the keystore's password.
	 * @param production true to use Apple's production servers, false to use the sandbox servers.
	 * @param devices a list or an array of tokens or devices: {@link java.lang.String String[]}, {@link java.util.List}<{@link java.lang.String}>, {@link javapns.devices.Device Device[]}, {@link java.util.List}<{@link javapns.devices.Device}>, {@link java.lang.String} or {@link javapns.devices.Device}
	 * @return a list of pushed notifications, each with details on transmission results and error (if any)
	 * @throws KeystoreException thrown if an error occurs when loading the keystore
	 * @throws CommunicationException thrown if an unrecoverable error occurs while trying to communicate with Apple servers
	 */
	public static PushedNotifications payload(Payload payload, Object keystore, String password, boolean production, Object devices, String serverIp) throws CommunicationException, KeystoreException {
		return sendPayload(payload, keystore, password, production, devices, serverIp);
	}


	/**
	 * Push a preformatted payload to a list of devices.
	 * 
	 * @param payload a simple or complex payload to push.
	 * @param keystore a keystore containing your private key and the certificate signed by Apple ({@link java.io.File}, {@link java.io.InputStream}, byte[], {@link java.security.KeyStore} or {@link java.lang.String} for a file path)
	 * @param password the keystore's password.
	 * @param production true to use Apple's production servers, false to use the sandbox servers.
	 * @param devices a list or an array of tokens or devices: {@link java.lang.String String[]}, {@link java.util.List}<{@link java.lang.String}>, {@link javapns.devices.Device Device[]}, {@link java.util.List}<{@link javapns.devices.Device}>, {@link java.lang.String} or {@link javapns.devices.Device}
	 * @return a list of pushed notifications, each with details on transmission results and error (if any)
	 * @throws KeystoreException thrown if an error occurs when loading the keystore
	 * @throws CommunicationException thrown if an unrecoverable error occurs while trying to communicate with Apple servers
	 */
	private static PushedNotifications sendPayload(Payload payload, Object keystore, String password, boolean production, Object devices, String serverIp) throws CommunicationException, KeystoreException {
		PushedNotifications notifications = new PushedNotifications();
		if (payload == null) return notifications;
		PushNotificationManager pushManager = new PushNotificationManager();
		try {
			AppleNotificationServer server = new AppleNotificationServerBasicImpl(keystore, password, production, serverIp);
			pushManager.initializeConnection(server);
			List<Device> deviceList = Devices.asDevices(devices);
			Devices.evaluateEfficiency(deviceList);
			notifications.setMaxRetained(deviceList.size());
			for (Device device : deviceList) {
				try {
					BasicDevice.validateTokenFormat(device.getToken());
					PushedNotification notification = pushManager.sendNotification(device, payload, false);
					notifications.add(notification);
				} catch (InvalidDeviceTokenFormatException e) {
					notifications.add(new PushedNotification(device, payload, e));
				}
			}
		} finally {
			try {
				pushManager.stopConnection();
			} catch (Exception e) {
			}
		}
		return notifications;
	}


	/**
	 * Push a preformatted payload to a list of devices using multiple simulatenous threads (and connections).
	 * 
	 * @param payload a simple or complex payload to push.
	 * @param keystore a keystore containing your private key and the certificate signed by Apple ({@link java.io.File}, {@link java.io.InputStream}, byte[], {@link java.security.KeyStore} or {@link java.lang.String} for a file path)
	 * @param password the keystore's password.
	 * @param production true to use Apple's production servers, false to use the sandbox servers.
	 * @param numberOfThreads the number of parallel threads to use to push the notifications
	 * @param devices a list or an array of tokens or devices: {@link java.lang.String String[]}, {@link java.util.List}<{@link java.lang.String}>, {@link javapns.devices.Device Device[]}, {@link java.util.List}<{@link javapns.devices.Device}>, {@link java.lang.String} or {@link javapns.devices.Device}
	 * @return a list of pushed notifications, each with details on transmission results and error (if any)
	 * @throws Exception thrown if any critical exception occurs
	 */
	public static PushedNotifications payload(Payload payload, Object keystore, String password, boolean production, int numberOfThreads, Object devices, String serverIp) throws Exception {
		if (numberOfThreads <= 0) return sendPayload(payload, keystore, password, production, devices, serverIp);
		AppleNotificationServer server = new AppleNotificationServerBasicImpl(keystore, password, production, serverIp);
		List<Device> deviceList = Devices.asDevices(devices);
		NotificationThreads threads = new NotificationThreads(server, payload, deviceList, numberOfThreads);
		threads.start();
		try {
			threads.waitForAllThreads(true);
		} catch (InterruptedException e) {
		}
		PushedNotifications p = threads.getPushedNotifications(true);
		threads.destroy();
		return p;
	}





	/**
	 * <p>Retrieve a list of devices that should be removed from future notification lists.</p>
	 * 
	 * <p>Devices in this list are ones that you previously tried to push a notification to,
	 * but to which Apple could not actually deliver because the device user has either
	 * opted out of notifications, has uninstalled your application, or some other conditions.</p>
	 * 
	 * <p>Important: Apple's Feedback Service always resets its list of inactive devices
	 * after each time you contact it.  Calling this method twice will not return the same
	 * list of devices!</p>
	 * 
	 * <p>Please be aware that Apple does not specify precisely when a device will be listed
	 * by the Feedback Service.  More specifically, it is unlikely that the device will
	 * be  listed immediately if you uninstall the application during testing.  It might
	 * get listed after some number of notifications couldn't reach it, or some amount of
	 * time has elapsed, or a combination of both.</p>
	 * 
	 * <p>Further more, if you are using Apple's sandbox servers, the Feedback Service will
	 * probably not list your device if you uninstalled your app and it was the last one
	 * on your device that was configured to receive notifications from the sandbox.
	 * See the library's wiki for more information.</p>
	 * 
	 * @param keystore a keystore containing your private key and the certificate signed by Apple ({@link java.io.File}, {@link java.io.InputStream}, byte[], {@link java.security.KeyStore} or {@link java.lang.String} for a file path)
	 * @param password the keystore's password.
	 * @param production true to use Apple's production servers, false to use the sandbox servers.
	 * @return a list of devices that are inactive.
	 * @throws KeystoreException thrown if an error occurs when loading the keystore
	 * @throws CommunicationException thrown if an unrecoverable error occurs while trying to communicate with Apple servers
	 */
	public static List<Device> feedback(Object keystore, String password, boolean production) throws CommunicationException, KeystoreException {
		List<Device> devices = new Vector<Device>();
		FeedbackServiceManager feedbackManager = new FeedbackServiceManager();
		AppleFeedbackServer server = new AppleFeedbackServerBasicImpl(keystore, password, production);
		devices.addAll(feedbackManager.getDevices(server));
		return devices;
	}

}
