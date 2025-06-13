UPDATE mleb_mcb.MESSAGE_PROPERTIES_I18N SET ERR_MESSAGE = 'Sorry, Transaction Amount must be more than minimum limit (100,000,000.00)' WHERE ERR_MESSAGE_CODE = 'omni.O0052' AND language_code = 'EN';
UPDATE mleb_mcb.MESSAGE_PROPERTIES_I18N SET ERR_MESSAGE = 'Maaf, Transaction Amount harus lebih dari minimum limit (100,000,000,00)' WHERE ERR_MESSAGE_CODE = 'omni.O0052' AND language_code = 'IN';

UPDATE mleb_mcb.MESSAGE_PROPERTIES_I18N SET ERR_MESSAGE = 'Sorry, Transaction Amount maximum limit is 1 ,000,000,000.00' WHERE ERR_MESSAGE_CODE = 'omni.O0053' AND language_code = 'EN';
UPDATE mleb_mcb.MESSAGE_PROPERTIES_I18N SET ERR_MESSAGE = 'Maaf, Maximum limit dari Transaction Amount adalah 1 ,000,000,000.00' WHERE ERR_MESSAGE_CODE = 'omni.O0053' AND language_code = 'IN';
