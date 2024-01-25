package com.healthcare.service;

import java.util.HashMap;
import java.util.List;

import com.healthcare.model.Appointments;
import com.healthcare.model.Doctors;
import com.healthcare.model.HealthHistory;
import com.healthcare.model.Medications;
import com.healthcare.model.Pharmacy;
import com.healthcare.model.Users;
import com.healthcare.vo.Response;

public interface UserDAL {

	public Response<Users> userLogin(Users user);

	public Response<String> addUser(Users user);

	public Response<String> addHealthHistory(HealthHistory healthHistory);
	
    public Response<String> addDoctorsDetails(Doctors doctor);

	public Response<List<Doctors>> fetchDoctorsDetails();

	public Response<HealthHistory> fetchPatientMedicalHistory(long user_id);

	public Response<String> updateDoctorDetails(Doctors doctor);

	public Response<String> bookAppointment(Appointments appointments);

	public Response<String> addPharmacyDetails(Pharmacy pharmacy);

	public Response<List<Pharmacy>> fetchPharmacyDetails();

	public Response<String> updatePharmacyDetails(Pharmacy pharmacy);

	public Response<HashMap> fetchMedicalHistory();

	public Response<String> deleteDoctorDetails(Doctors doctor);

	public Response<String> bookPharmacyAppointment(Medications  appointments);

	public Response<String> deletePharmacyDetails(Pharmacy doctor);

//	public Response<String> uploadPrescription(Medications appointments);

}
