INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'PUSH_NOTI_TITLE', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'PUSH_NOTI_TITLE');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Velocity Mobile OCBC NISP', 'PUSH_NOTI_TITLE', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'PUSH_NOTI_TITLE' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Velocity Mobile OCBC NISP', 'PUSH_NOTI_TITLE', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'PUSH_NOTI_TITLE' AND language_code = 'EN');

INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'PUSH_NOTI_BOUND_DEVICE', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'PUSH_NOTI_BOUND_DEVICE');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'You bound $1 to Velocity Mobile OCBC NISP. Please login to check your bound device', 'PUSH_NOTI_BOUND_DEVICE', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'PUSH_NOTI_BOUND_DEVICE' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'You bound $1 to Velocity Mobile OCBC NISP. Please login to check your bound device', 'PUSH_NOTI_BOUND_DEVICE', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'PUSH_NOTI_BOUND_DEVICE' AND language_code = 'EN');