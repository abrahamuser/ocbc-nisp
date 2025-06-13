INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'omni.O0120', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'omni.O0120');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Silakan hubungi clientservices@ocbcnisp.com untuk memperbarui kata sandi anda.', 'omni.O0120', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'omni.O0120' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Please contact clientservices@ocbcnisp.com to reset your password.', 'omni.O0120', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'omni.O0120' AND language_code = 'EN');