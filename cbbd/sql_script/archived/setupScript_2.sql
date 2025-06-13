INSERT INTO mleb_mcb.mcb_conf (row_id,ao_alpha_numeric_regex,ao_alpha_regex,ao_name_regex,cache_expired_hour,creation_by,creation_date,currency,default_language,description,device_limit,exponent,final_cache_expired_hour,general_regex,input_token_size,mib_type,modulus,resend_token_limit,session_duration,status,update_by,update_date,upfront_notice_maintnc_time,upfront_notice_time,login_limit_access) VALUES (mleb_mcb.MiBConf_id.nextval,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,999999,'ACTIVE',NULL,NULL,30,30,'[SB]');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'maxDeviceBinding','3',NULL,'A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'VM.0001','Online FX','menuAccess','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'VM.0002','Open New Account','menuAccess','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'10217','Consolidated View','menuAccess','A');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'tnc_general','tnc001.html','tnc','A');



INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'IFT','Internal Fund Transfer','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'OAT','Own Account Transfer','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'OLT','Online Fund Transfer','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'LLG','LLG/SKN','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'RTGS','RTGS','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TT','Telegraphic Transfer','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'ASI','Account Swept In','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BPM','Bill Presentment','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'ETAX','Etax Payment ','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'ONFX','ONFX Transaction','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TDAO','Time Deposit Account Opening','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TDAM','Time Deposit Account Maintenance','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'CSWTH','Cash Withdrawal','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLIDR','Bulk Payment IDR','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLIDRGB','Velocity V1 - Bulk Payment IDR','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLFCY','Bulk Payment Foreign','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'BLFCYGB','Velocity V1 - Bulk Payment Foreign','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TON','Etax Upload Online','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TOF','Etax Upload Batch ','TRANXPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'VA','VA Member Upload','TRANXPRODCODE','A');


INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'MN','IFT,OAT,OLT,LLG,RTGS,TT,ASI,BPM,ETAX,ONFX,TDAO,TDAM,CSWTH','TASKMAPPRODCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'NB','IFT,OAT,OLT,LLG,RTGS,TT,BPM,ETAX','TASKMAPPRODCODE','A');






INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'000','Mix Status','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'000','Status Campuran','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'001','Saved','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'001','Disimpan','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'002','Deleted','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'002','Dihapus','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'003','Pending Verification','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'003','Verifikasi Tertunda','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'004','Pending Authorization','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'004','Otorisasi Tertunda','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'005','Authorized','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'005','Telah Diotorisasi','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'006','Cancelled','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'006','Dibatalkan','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'007','Auth Rejected','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'007','Otorisasi Ditolak','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'008','Repushed Pending Auth','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'008','Repushed Otorisasi yang Tertunda','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'009','Repushed Approved','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'009','Otorisasi Repushed','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'010','Repush Cancelled','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'010','Batal Kirim Ulang','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'011','In Progress','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'011','Dalam Proses','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'012','Completed','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'012','Selesai','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'013','Rejected','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'013','Ditolak','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'014','Suspected','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'014','Diinvestigasi','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'015','Uploaded','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'015','Terupload','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'016','Upload Failed','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'016','Upload Gagal','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'018','Processed','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'018','Sudah Diproses','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'020','Cancel Txn Pending Auth','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'020','Batalkan Transaksi yang Tertunda','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'021','Processing','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'021','Sedang Diproses','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'022','Part Completed','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'022','Selesai Sebagian','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'023','Part Rejected','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'023','Ditolak Sebagian','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'025','Pending Release by Bank','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'025','Release Tertunda oleh Bank','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'027','Ready to Submit for Authorization','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'027','Siap Diajukan untuk Otorisasi','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'028','Inquiry Rejected','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'028','Inquiry Ditolak','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'030','Inquiry Success','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'030','Inquiry Berhasil','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'031','Fetch In Progress','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'031','Sedang Berlangsung','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'032','Transaction In Progress','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'032','Transaction In Progress','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'034','Final Process','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'034','Proses Akhir','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'035','Not Executed','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'035','Tidak Diproses','TRANXSTATUSCODE_IN','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'036','Ready For Collection','TRANXSTATUSCODE','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'036','Siap Untuk Pengambilan','TRANXSTATUSCODE_IN','A');



INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'US002','','blockStatusState','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'US003','','blockStatusState','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TE002','','blockStatusState','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TE003','','blockStatusState','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TE004','','blockStatusState','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TE005','','blockStatusState','A');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) VALUES (mleb_mcb.GeneralCode_id.nextval,'TE007','','blockStatusState','A');
