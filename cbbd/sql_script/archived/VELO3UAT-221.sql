INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, 'whatsapp_url', 'https://api.whatsapp.com/send?phone=+628121500999&text=Hi%20OCBC%20NISP', 'redirectUrl', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'redirectUrl' AND gncode='whatsapp_url');
