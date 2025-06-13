
UPDATE MLEB_MCB.MESSAGE_PROPERTIES_I18N SET err_message='Transfer dana dalam Rupiah dengan minimum transfer Rp %min_amt%/transaksi. Batas waktu transaksi pk %end_time% (WIB).' WHERE err_message_code = 'MENU_LIST_DESC_DYNM_RTGS' AND language_code = 'IN';
UPDATE MLEB_MCB.MESSAGE_PROPERTIES_I18N SET err_message='Fund transfers in IDR with minimum transfer IDR %min_amt%/transaction. Cut-Off Time transaction is at %end_time% (West Indonesia Time Zone).' WHERE err_message_code = 'MENU_LIST_DESC_DYNM_RTGS' AND language_code = 'EN';
