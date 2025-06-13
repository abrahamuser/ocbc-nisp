INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'MENU_LIST_DESC_DYNM_FAST', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'MENU_LIST_DESC_DYNM_FAST');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Transfer dana dalam Rupiah sampai dengan Rp 250,000,000/transaksi. Layanan tersedia setiap saat.', 'MENU_LIST_DESC_DYNM_FAST', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_FAST' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Fund transfers in IDR up to 250,000,000/transaction through Bank Indonesia (real time). Available at any time.', 'MENU_LIST_DESC_DYNM_FAST', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_FAST' AND language_code = 'EN');