INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'WSS_ERROR_00001', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'WSS_ERROR_00001');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Sesi Anda telah selesai karena tidak aktif. Silakan Login kembali.', 'WSS_ERROR_00001', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'WSS_ERROR_00001' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Your session has timed out due to inactivity. Please login again.', 'WSS_ERROR_00001', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'WSS_ERROR_00001' AND language_code = 'EN');