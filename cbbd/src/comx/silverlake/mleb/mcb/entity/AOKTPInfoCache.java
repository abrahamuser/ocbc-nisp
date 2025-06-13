package comx.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "AO_KTP_Info_Cache")
public class AOKTPInfoCache implements java.io.Serializable{

	private Integer rowId;
	private String deviceId;
	private String agama;
	private String alamat;
	private String dusun;
	private String eKTPStatus;
	private String golonganDarah;
	private String jenisKelamin;
	private String jenisPekerjaan;
	private String kabName;
	private String kecamatanName;
	private String kelurahanName;
	private String kodePos;
	private String namaLengkap;
	private String namaLengkapAyah;
	private String namaLengkapIbu;
	private String nik;
	private String noKK;
	private String noKabupaten;
	private String noKecamatan;
	private String noKelurahan;
	private String noProvinsi;
	private String noRT;
	private String noRW;
	private String pendidikanAkhir;
	private String penyandangCacat;
	private String provinsiName;
	private String statusHubKel;
	private String statusKawin;
	private String tanggalLahir;
	private String tempatLahir;
	private String status;
	private String isFinal;
	private String userId;
	private String moduleCode;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "AO_KTP_INFO_CACHE_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "device_id")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@Column(name = "agama")
	public String getAgama() {
		return agama;
	}
	public void setAgama(String agama) {
		this.agama = agama;
	}
	@Column(name = "alamat")
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	@Column(name = "dusun")
	public String getDusun() {
		return dusun;
	}
	public void setDusun(String dusun) {
		this.dusun = dusun;
	}
	@Column(name = "eKTPStatus")
	public String geteKTPStatus() {
		return eKTPStatus;
	}
	public void seteKTPStatus(String eKTPStatus) {
		this.eKTPStatus = eKTPStatus;
	}
	@Column(name = "golonganDarah")
	public String getGolonganDarah() {
		return golonganDarah;
	}
	public void setGolonganDarah(String golonganDarah) {
		this.golonganDarah = golonganDarah;
	}
	@Column(name = "jenisKelamin")
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	@Column(name = "jenisPekerjaan")
	public String getJenisPekerjaan() {
		return jenisPekerjaan;
	}
	public void setJenisPekerjaan(String jenisPekerjaan) {
		this.jenisPekerjaan = jenisPekerjaan;
	}
	@Column(name = "kabName")
	public String getKabName() {
		return kabName;
	}
	public void setKabName(String kabName) {
		this.kabName = kabName;
	}
	@Column(name = "kecamatanName")
	public String getKecamatanName() {
		return kecamatanName;
	}
	public void setKecamatanName(String kecamatanName) {
		this.kecamatanName = kecamatanName;
	}
	@Column(name = "kelurahanName")
	public String getKelurahanName() {
		return kelurahanName;
	}
	public void setKelurahanName(String kelurahanName) {
		this.kelurahanName = kelurahanName;
	}
	@Column(name = "kodePos")
	public String getKodePos() {
		return kodePos;
	}
	public void setKodePos(String kodePos) {
		this.kodePos = kodePos;
	}
	@Column(name = "namaLengkap")
	public String getNamaLengkap() {
		return namaLengkap;
	}
	public void setNamaLengkap(String namaLengkap) {
		this.namaLengkap = namaLengkap;
	}
	@Column(name = "namaLengkapAyah")
	public String getNamaLengkapAyah() {
		return namaLengkapAyah;
	}
	public void setNamaLengkapAyah(String namaLengkapAyah) {
		this.namaLengkapAyah = namaLengkapAyah;
	}
	@Column(name = "namaLengkapIbu")
	public String getNamaLengkapIbu() {
		return namaLengkapIbu;
	}
	public void setNamaLengkapIbu(String namaLengkapIbu) {
		this.namaLengkapIbu = namaLengkapIbu;
	}
	@Column(name = "nik")
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	@Column(name = "noKK")
	public String getNoKK() {
		return noKK;
	}
	public void setNoKK(String noKK) {
		this.noKK = noKK;
	}
	@Column(name = "noKabupaten")
	public String getNoKabupaten() {
		return noKabupaten;
	}
	public void setNoKabupaten(String noKabupaten) {
		this.noKabupaten = noKabupaten;
	}
	@Column(name = "noKecamatan")
	public String getNoKecamatan() {
		return noKecamatan;
	}
	public void setNoKecamatan(String noKecamatan) {
		this.noKecamatan = noKecamatan;
	}
	@Column(name = "noKelurahan")
	public String getNoKelurahan() {
		return noKelurahan;
	}
	public void setNoKelurahan(String noKelurahan) {
		this.noKelurahan = noKelurahan;
	}
	@Column(name = "noProvinsi")
	public String getNoProvinsi() {
		return noProvinsi;
	}
	public void setNoProvinsi(String noProvinsi) {
		this.noProvinsi = noProvinsi;
	}
	@Column(name = "noRT")
	public String getNoRT() {
		return noRT;
	}
	public void setNoRT(String noRT) {
		this.noRT = noRT;
	}
	@Column(name = "noRW")
	public String getNoRW() {
		return noRW;
	}
	public void setNoRW(String noRW) {
		this.noRW = noRW;
	}
	@Column(name = "pendidikanAkhir")
	public String getPendidikanAkhir() {
		return pendidikanAkhir;
	}
	public void setPendidikanAkhir(String pendidikanAkhir) {
		this.pendidikanAkhir = pendidikanAkhir;
	}
	@Column(name = "penyandangCacat")
	public String getPenyandangCacat() {
		return penyandangCacat;
	}
	public void setPenyandangCacat(String penyandangCacat) {
		this.penyandangCacat = penyandangCacat;
	}
	@Column(name = "provinsiName")
	public String getProvinsiName() {
		return provinsiName;
	}
	public void setProvinsiName(String provinsiName) {
		this.provinsiName = provinsiName;
	}
	@Column(name = "statusHubKel")
	public String getStatusHubKel() {
		return statusHubKel;
	}
	public void setStatusHubKel(String statusHubKel) {
		this.statusHubKel = statusHubKel;
	}
	@Column(name = "statusKawin")
	public String getStatusKawin() {
		return statusKawin;
	}
	public void setStatusKawin(String statusKawin) {
		this.statusKawin = statusKawin;
	}
	@Column(name = "tanggalLahir")
	public String getTanggalLahir() {
		return tanggalLahir;
	}
	public void setTanggalLahir(String tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}
	@Column(name = "tempatLahir")
	public String getTempatLahir() {
		return tempatLahir;
	}
	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "is_final")
	public String getIsFinal() {
		return isFinal;
	}
	public void setIsFinal(String isFinal) {
		this.isFinal = isFinal;
	}
	
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "module_code")
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	
}
