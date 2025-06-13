INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'GFT', 'General Fund Transfer', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='GFT');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'OP', 'Online Payment', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='OP');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'GBFT', 'Velocity V1 - General Fund Transfer', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='GBFT');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'BLIDR', 'Bulk Payment IDR', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='BLIDR');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'BLIDRGB', 'Velocity V1 - Bulk Payment IDR', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='BLIDRGB');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'BLFCY', 'Bulk Payment Foreign', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='BLFCY');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'BLFCYGB', 'Velocity V1 - Bulk Payment Foreign', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='BLFCYGB');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'TON', 'Etax Upload Online', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='TON');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'TOF', 'Etax Upload Batch', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='TOF');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'VA', 'VA Member Upload', 'TRANXUPLOADFORMAT_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXUPLOADFORMAT_IN' AND gncode='VA');

UPDATE mleb_mcb.generalcode SET gncode_type='TRANXUPLOADFORMAT_EN' WHERE gncode_type='TRANXUPLOADFORMAT';

