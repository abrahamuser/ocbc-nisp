INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'MENU_LIST_DESC_DYNM_OLT', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'MENU_LIST_DESC_DYNM_OLT');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Transfer dana dalam Rupiah melalui jaringan ATM Bersama / ATM Prima (real time). Maksimum transfer Rp %min_amt%/transaksi dan Rp %max_amt%/hari. Layanan tersedia setiap saat.', 'MENU_LIST_DESC_DYNM_OLT', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_OLT' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Fund transfers in IDR through ATM Bersama / ATM Prima (real time). Maximum transfer IDR %min_amt%/transaction and IDR %max_amt%/day. Available at any time.', 'MENU_LIST_DESC_DYNM_OLT', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_OLT' AND language_code = 'EN');

INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'MENU_LIST_DESC_DYNM_LLG', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'MENU_LIST_DESC_DYNM_LLG');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Transfer dana dalam Rupiah sampai dengan Rp %max_amt%/transaksi. Batas waktu transaksi pk %end_time% (WIB).', 'MENU_LIST_DESC_DYNM_LLG', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_LLG' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Fund transfers in IDR up to %max_amt%/transaction. Cut-Off Time transaction is at %end_time% (West Indonesia Time Zone).', 'MENU_LIST_DESC_DYNM_LLG', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_LLG' AND language_code = 'EN');

INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'MENU_LIST_DESC_DYNM_RTGS', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'MENU_LIST_DESC_DYNM_RTGS');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Transfer dana dalam Rupiah dengan minimum transfer lebih dari Rp %min_amt%/transaksi. Batas waktu transaksi pk %end_time% (WIB).', 'MENU_LIST_DESC_DYNM_RTGS', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_RTGS' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Fund transfers in IDR with minimum transfer more than IDR %min_amt%/transaction. Cut-Off Time transaction is at %end_time% (West Indonesia Time Zone).', 'MENU_LIST_DESC_DYNM_RTGS', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_RTGS' AND language_code = 'EN');

INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES (message_id, create_by, create_dt, err_message_code, status) 
SELECT MLEB_MCB.MESSAGEPROPERTIES_ID.nextval, 'system', SYSDATE, 'MENU_LIST_DESC_DYNM_TT', 'ACTIVE' FROM MLEB_MCB.MESSAGE_PROPERTIES 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES WHERE err_message_code = 'MENU_LIST_DESC_DYNM_TT');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Transfer dana dalam berbagai mata uang asing ke semua bank, domestik dan luar negeri. Batas waktu transaksi pk %end_time% (WIB).', 'MENU_LIST_DESC_DYNM_TT', 'IN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_TT' AND language_code = 'IN');
INSERT INTO MLEB_MCB.MESSAGE_PROPERTIES_I18N (row_id, create_by, create_dt, err_message, err_message_code, language_code) 
SELECT MLEB_MCB.MESSAGEPROPERTIESI18N_ID.nextval, 'system', SYSDATE, 'Fund transfers in various foreign currencies to all banks, both domestic and overseas. Cut-Off Time transaction is at %end_time% (West Indonesia Time Zone).', 'MENU_LIST_DESC_DYNM_TT', 'EN' FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE ROWNUM=1 AND NOT EXISTS
(SELECT err_message_code FROM MLEB_MCB.MESSAGE_PROPERTIES_I18N WHERE err_message_code = 'MENU_LIST_DESC_DYNM_TT' AND language_code = 'EN');

