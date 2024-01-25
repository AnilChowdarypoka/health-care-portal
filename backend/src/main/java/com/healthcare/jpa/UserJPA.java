package com.healthcare.jpa;

import java.util.HashMap;
import java.util.List;

import com.healthcare.model.Appointments;
import com.healthcare.model.Doctors;
import com.healthcare.model.HealthHistory;
import com.healthcare.model.Medications;
import com.healthcare.model.Pharmacy;
import com.healthcare.model.Users;
import com.healthcare.vo.Response;

public interface UserJPA {

	public Response<String> addUser(Users user);

	public Response<Users> userLogin(Users user);

	public Response<String> addHealthHistory(HealthHistory healthHistory);

	public Response<String> addDoctors(Doctors doctor);

	public Response<Users> fetchUser(long userId);

	public Response<List<Doctors>> fetchDoctors();

	public Response<HealthHistory> fetchPatientMedicalHistory(long user_id);

	public Response<String> updateDoctors(Doctors doctor);

	public Doctors fetchDoctors(long doctorId);

	public Response<String> bookAppointment(Appointments appointments);

	public Response<String> addPharmacy(Pharmacy pharmacy);

	public Response<List<Pharmacy>> fetchPharmacyDetails();

	public Pharmacy fetchPharmacy(long id );

	public Response<String> updatePharmacy(Pharmacy pharmacy);

	public Response<List<Users>> fetchUsers();

//	public Response<List<HealthHistory>> fetchPatientMedicalHistory();

	Response<List<HealthHistory>> fetchPatientMedicalHistoryAll(long user_id);

	public Response<String> deleteDoctorDetails(long l);

	Response<String> deleteDoctorDetails(Doctors doc);

	public Response<String> bookMedicalAppointment(Medications appointments);

	public Response<String> deletePharmacyDetails(Pharmacy pharmacy);


}
