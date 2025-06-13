--- biometric
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='VM.0017';
-- open new account
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='VM.0002';
-- tdao
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='5601';
-- tdam
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='5602';
-- unit trust
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='9501';
-- bonds
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='9502';
-- loans
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='9401';
-- spt
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='9301';
-- my account
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='VM.0019';
-- deposite account
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='VM.0020';
-- time deposit
UPDATE mleb_mcb.generalcode SET status='A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='VM.0021';