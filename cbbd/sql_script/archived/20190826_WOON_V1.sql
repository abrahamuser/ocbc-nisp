delete mleb_mcb.generalcode where gncode_type = 'TRANXPRODCODE';


INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'IFT','Transfer Dana ke OCBC NISP','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'OAT','Transfer Dana ke Rekening Sendiri','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'OLT','Transfer Online','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'LLG','LLG/SKN','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'RTGS','RTGS','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TT','Transfer Valuta Asing','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'ASI','Sweep In Rekening','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BPM','Pembayaran Tagihan','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'ETAX','Pembayaran Pajak','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'ONFX','Transaksi ONFX','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TDAO','Pembukaan Rekening Deposito','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TDAM','Pemeliharaan Perpanjangan Deposito','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'CSWTH','Penarikan Tunai','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLIDR','Pembayaran Bulk IDR','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLIDRGB','Velocity V1 - Pembayaran Bulk IDR','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLFCY','Pembayaran Bulk Valuta Asing','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLFCYGB','Velocity V1 - Pembayaran Bulk Valuta Asing','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TON','E-Tax Upload Online','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TOF','E-Tax Upload Batch','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'VA','Unggah Anggota VA','TRANXPRODCODE_IN','A');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'SPIDR','Pembayaran Bulk Supplier IDR','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'PYIDR','Pembayaran Bulk Payroll IDR','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'SPFCY','Pembayaran Bulk Supplier Valuta Asing','TRANXPRODCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'PYFCY','Pembayaran Bulk Payroll Valuta Asing ','TRANXPRODCODE_IN','A');





INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'IFT','Internal Fund Transfer','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'OAT','Own Fund Transfer','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'OLT','Online Transfer','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'LLG','LLG/SKN','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'RTGS','RTGS','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TT','Telegraphic Transfer','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'ASI','Account Sweep In','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BPM','Bill Presentment','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'ETAX','E-Tax Payment','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'ONFX','ONFX Transaction','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TDAO','Time Deposit Account Opening','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TDAM','Time Deposit Rollover Maintenance','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'CSWTH','Cash Withdrawal','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLIDR','Bulk Payment IDR','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLIDRGB','Velocity V1 - Bulk Payment IDR','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLFCY','Bulk Payment Foreign','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLFCYGB','Velocity V1 - Bulk Payment Foreign','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TON','E-Tax Upload Online','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TOF','E-Tax Upload Batch','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'VA','VA Upload Member','TRANXPRODCODE','A');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'SPIDR','Bulk Payment Supplier IDR','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'PYIDR','Bulk Payment Payroll IDR','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'SPFCY','Bulk Payment Supplier Foreign','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'PYFCY','Bulk Payment Payroll Foreign','TRANXPRODCODE','A');