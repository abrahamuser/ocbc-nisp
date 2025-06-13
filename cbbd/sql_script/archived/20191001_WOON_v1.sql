UPDATE MLEB_MCB.MESSAGE_PROPERTIES_I18N SET ERR_MESSAGE = 'Fund transfers in IDR up to 1 Billion' WHERE ERR_MESSAGE_CODE = 'MENU_LIST_DESC_5104' AND LANGUAGE_CODE='en';
UPDATE MLEB_MCB.MESSAGE_PROPERTIES_I18N SET ERR_MESSAGE = 'Fund transfers in IDR above 100 Million' WHERE ERR_MESSAGE_CODE = 'MENU_LIST_DESC_5103' AND LANGUAGE_CODE='en';


INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'dev.binding.max.sms.timeInterval','10','dev.binding.sms','A');
