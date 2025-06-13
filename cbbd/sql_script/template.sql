-- insert message properties
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'omni.O0095', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'omni.O0095');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Description of error', 'omni.O0095', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'omni.O0095' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Description of error', 'omni.O0095', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'omni.O0095' AND language_code = 'EN');

-- update message properties
UPDATE MLEB_MCB.MESSAGE_PROPERTIES_I18N SET err_message='CONTENT' WHERE err_message_code = 'omni.O0095' AND language_code = 'IN';
UPDATE MLEB_MCB.MESSAGE_PROPERTIES_I18N SET err_message='CONTENT' WHERE err_message_code = 'omni.O0095' AND language_code = 'EN';

-- insert access restriction
-- access_validate - when turn to 1, call omni user/access_restriction to validate (hide when mn.act.new exist)
-- role_validate - when value more than 0, role_type must fill up for role checking (1 = hide when role not exist, 2 = hide when role exist)
-- menu_validate - when turn to 1, menu_validate_id must fill up to check against menu_access_list returned from omni (hide if not exist)
-- block_validate - when turn to 1, use menuAccess from generalcode to determine the module need to be block or not

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT 'VM.0007', '0', 'Task List', 0, 0, NULL, 0, 1, '5100' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = 'VM.0007');

-- insert general code
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '024', 'Rejected', 'TRANXSTATUSCODE', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE' AND gncode='024');

-- insert general code (menuAccess)
-- X for disable
-- A for enable
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, 'VM.0008', 'Task List', 'menuAccess', 'X' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='VM.0008');
-- update menuAccess
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='VM.0008';

-- insert access_restriction_trx
INSERT INTO mleb_mcb.access_restriction_trx (SOURCE_CODE, PROD_CODE, MENU_ID, MENU_DESCRIPTION, STATUS, IS_NEW, IS_EDIT, IS_CANCEL, IS_NOTE, IS_AUDIT, IS_PRINT, IS_DELETE)
SELECT 'MN', 'IFT', '5101', 'Saved', '001', 'N', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y' FROM mleb_mcb.access_restriction_trx 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT SOURCE_CODE FROM mleb_mcb.access_restriction_trx WHERE SOURCE_CODE = 'MN' AND PROD_CODE='IFT' AND STATUS='001' );

-- insert version control
INSERT INTO MLEB_MCB.VERSION_CONTROL (ROW_ID, APP_NAME, STATUS, UPDATE_DATE, VERSION, DISPLAY_VERSION) 
SELECT MLEB_MCB.VERSIONCONTROL_ID.nextval, 'android', 'A', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), '1', '1.0.0' FROM MLEB_MCB.VERSION_CONTROL 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT APP_NAME FROM MLEB_MCB.VERSION_CONTROL WHERE APP_NAME = 'android' AND VERSION='1');

-- update force update
UPDATE mleb_mcb.generalcode SET GNCODE_DESCRIPTION='VERSION NO HERE' WHERE GNCODE_TYPE='SERVER_VERSION' AND GNCODE='android';
UPDATE mleb_mcb.generalcode SET GNCODE_DESCRIPTION='VERSION NO HERE' WHERE GNCODE_TYPE='SERVER_VERSION' AND GNCODE='ios';

-- insert tnc
DELETE FROM MLEB_MCB.TNC WHERE PRODUCT_KEY = 'PRB001';
INSERT INTO MLEB_MCB.TNC (ROW_ID, PRODUCT_KEY, TNC_URL, FOOTER, LOCALE) VALUES (MLEB_MCB.TNC_ID.nextval, 'PRB001', '/home/oracle/ocbc/apps/tnc/PRB001.txt', '', 'EN');
INSERT INTO MLEB_MCB.TNC (ROW_ID, PRODUCT_KEY, TNC_URL, FOOTER, LOCALE) VALUES (MLEB_MCB.TNC_ID.nextval, 'PRB001', '/home/oracle/ocbc/apps/tnc/PRB001_IN.txt', '', 'IN');
-- insert tnc checkbox
DELETE FROM MLEB_MCB.TNC_CHECKBOX WHERE PRODUCT_KEY = 'PRB001';
INSERT INTO MLEB_MCB.TNC_CHECKBOX (ROW_ID, CONTENT, LOCALE, PRODUCT_KEY, SEQ_NO) values (MLEB_MCB.TNC_CHECKBOX_ID.nextval, 'I have read, understand, and agree to the Product Terms & Conditions', 'EN', 'MODULECODE001', 1);
INSERT INTO MLEB_MCB.TNC_CHECKBOX (ROW_ID, CONTENT, LOCALE, PRODUCT_KEY, SEQ_NO) values (MLEB_MCB.TNC_CHECKBOX_ID.nextval, 'Saya telah membaca, memahami, dan menyetujui Syarat & Ketentuan Produk', 'IN', 'MODULECODE001', 1);
-- update server maintenance notice time
-- second for notice to pop up, minutes before the notice should show before the actual start datetime of maintenance
UPDATE MLEB_MCB.MCB_CONF SET UPFRONT_NOTICE_TIME = 30, UPFRONT_NOTICE_MAINTNC_TIME=30 WHERE STATUS='ACTIVE';
-- insert server maintenance down time, (For os version put over here, any version below will only trigger the maintenance pop up)
DELETE FROM MLEB_MCB.maintenance_notification WHERE status = 'ACTIVE';
INSERT INTO MLEB_MCB.maintenance_notification (maintenance_id, description, effective_start_dt, effective_end_dt, status) VALUES 
(MLEB_MCB.MaintenanceNotification_id.nextval, 'android=12,ios=3', TO_DATE('2020/10/23 21:00:00', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2020/10/024 03:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'ACTIVE');

