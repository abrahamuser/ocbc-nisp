
UPDATE mleb_mcb.generalcode SET gncode_description='IFT,OAT,OLT,LLG,RTGS,TT,ASI,ASO,BPM,ETAX,ONFX,TDAO,TDAM,CSWTH,FAST' WHERE gncode_type = 'TASKMAPPRODCODE' AND gncode='MN';


UPDATE mleb_mcb.generalcode SET gncode_description='IFT,OAT,OLT,LLG,RTGS,TT,BPM,ETAX,FAST' WHERE gncode_type = 'TASKMAPPRODCODE' AND gncode='NB';
