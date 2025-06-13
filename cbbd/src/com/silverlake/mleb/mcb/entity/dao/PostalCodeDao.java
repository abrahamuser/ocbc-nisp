package com.silverlake.mleb.mcb.entity.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.PostalCode;
import comx.silverlake.mleb.mcb.entity.PostalCodeWhitelist;


@Service
@Repository
@Transactional
public class PostalCodeDao extends DAO
{
	private static Logger log = LogManager.getLogger(PostalCodeDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<PostalCode> getAllProvince()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCode.class.getSimpleName();
		List<PostalCode> listPostal = em.createQuery(sql).getResultList();
		return listPostal;
	}
	
	public List<String> getAllProvinceFiltered(){
		EntityManager em  = getEntityManager();
		List<String> results = em
		        .createQuery("SELECT province FROM "+PostalCode.class.getSimpleName()+" GROUP BY province ORDER BY province ASC")
		        .getResultList();
		return results;
	}
	
	public List<PostalCode> getListByPostalCode(String postalCodeInput)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCode.class.getSimpleName()+" WHERE postal_code = :postalCode";
		List<PostalCode> listPostal = em.createQuery(sql).setParameter("postalCode", postalCodeInput).getResultList();
		return listPostal;
	}
	
	public List<PostalCode> getListByProvince(String province)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCode.class.getSimpleName()+" WHERE province = :province";
		List<PostalCode> listPostal = em.createQuery(sql).setParameter("province", province).getResultList();
		return listPostal;
	}
	
	public List<PostalCode> getListByKabupaten(String kabupaten)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCode.class.getSimpleName()+" WHERE kabupaten = :kabupaten";
		List<PostalCode> listPostal = em.createQuery(sql).setParameter("kabupaten", kabupaten).getResultList();
		return listPostal;
	}
	
	public List<PostalCode> getListByKecamatan(String kecamatan)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCode.class.getSimpleName()+" WHERE kecamatan = :kecamatan";
		List<PostalCode> listPostal = em.createQuery(sql).setParameter("kecamatan", kecamatan).getResultList();
		return listPostal;
	}
	
	public List<PostalCode> getListByKelurahan(String kelurahan)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCode.class.getSimpleName()+" WHERE kelurahan = :kelurahan";
		List<PostalCode> listPostal = em.createQuery(sql).setParameter("kelurahan", kelurahan).getResultList();
		return listPostal;
	}
	
	public List<PostalCode> getListByProvinceKabupaten(String province, String kabupaten)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCode.class.getSimpleName()+" WHERE province = :province AND kabupaten = :kabupaten";
		List<PostalCode> listPostal = em.createQuery(sql).setParameter("province", province).setParameter("kabupaten", kabupaten).getResultList();
		return listPostal;
	}
	
	public List<PostalCode> getListByProvinceKabupatenKecamatan(String province, String kabupaten, String kecamatan)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCode.class.getSimpleName()+" WHERE province = :province AND kabupaten = :kabupaten AND kecamatan = :kecamatan";
		List<PostalCode> listPostal = em.createQuery(sql).setParameter("province", province).setParameter("kabupaten", kabupaten).setParameter("kecamatan", kecamatan).getResultList();
		return listPostal;
	}
	
	public List<PostalCode> getListByAll(String province, String kabupaten, String kecamatan, String kelurahan)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCode.class.getSimpleName()+" WHERE province = :province AND kabupaten = :kabupaten AND kecamatan = :kecamatan AND kelurahan = :kelurahan";
		List<PostalCode> listPostal = em.createQuery(sql).setParameter("province", province).setParameter("kabupaten", kabupaten).setParameter("kecamatan", kecamatan).setParameter("kelurahan", kelurahan).getResultList();
		return listPostal;
	}
	
	public List<PostalCodeWhitelist> getWhitelistPostalCode(String postalCode){
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PostalCodeWhitelist.class.getSimpleName()+" WHERE postalCode = :postalCode";;
		List<PostalCodeWhitelist> listPostal = em.createQuery(sql).setParameter("postalCode", postalCode).getResultList();
		return listPostal;
	}
	
	public List<String> getKabupatenListByProvince(String province){
		EntityManager em  = getEntityManager();
		String sql = "SELECT kabupaten FROM "+PostalCode.class.getSimpleName()+" WHERE province = :province GROUP BY kabupaten ORDER BY kabupaten ASC";
		List<String> results = em.createQuery(sql).setParameter("province", province).getResultList();
		return results;
	}
	
	public List<String> getKecamatanListByProvinceKabupaten(String province, String kabupaten)
	{
		EntityManager em  = getEntityManager();
		String sql = "SELECT kecamatan FROM "+PostalCode.class.getSimpleName()+" WHERE province = :province AND kabupaten = :kabupaten GROUP BY kecamatan ORDER BY kecamatan ASC";
		List<String> results = em.createQuery(sql).setParameter("province", province).setParameter("kabupaten", kabupaten).getResultList();
		return results;
	}
	
	public List<String> getKelurahanListByProvinceKabupatenKecamatan(String province, String kabupaten, String kecamatan)
	{
		EntityManager em  = getEntityManager();
		String sql = "SELECT kelurahan FROM "+PostalCode.class.getSimpleName()+" WHERE province = :province AND kabupaten = :kabupaten AND kecamatan = :kecamatan GROUP BY kelurahan ORDER BY kelurahan ASC";
		List<String> results = em.createQuery(sql).setParameter("province", province).setParameter("kabupaten", kabupaten).setParameter("kecamatan", kecamatan).getResultList();
		return results;
	}
	
	public int removePostalCode(){
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+PostalCode.class.getSimpleName();
		return em.createQuery(sql).executeUpdate();

	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}