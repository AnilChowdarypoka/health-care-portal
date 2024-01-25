package com.healthcare.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.HealthCarePortalApplication;
import com.healthcare.model.Appointments;
import com.healthcare.model.Doctors;
import com.healthcare.model.EmailDetails;
import com.healthcare.model.HealthHistory;
import com.healthcare.vo.AppointmentsVO;
import com.healthcare.vo.MedicationsVO;
import com.healthcare.model.Medications;
import com.healthcare.model.Pharmacy;
import com.healthcare.model.Users;
import com.healthcare.service.EmailService;
import com.healthcare.service.UserDAL;
import com.healthcare.vo.Response;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired 
	private HealthCarePortalApplication healthCarePortalApplication;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDAL userDAL;
	
	@Autowired 
	private EmailService emailService;


	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "Hi welcome,";

	}

	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public Response<String> addNewUsers(@RequestBody Users user) {
		log.info("creating user.....");
		// LOG.info(user.getDob().toGMTString());
		return userDAL.addUser(user);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response<Users> loginUser(@RequestBody Users user) {
		log.info("logging in.....");
		return userDAL.userLogin(user);

	}

	@RequestMapping(value = "/add-medical-history", method = RequestMethod.POST)
	public Response<String> addMedicalHistory(@RequestBody HealthHistory healhHistory) {
		log.info("adding medical history.....");
		return userDAL.addHealthHistory(healhHistory);

	}

	@RequestMapping(value = "/add-doctors-details", method = RequestMethod.POST)
	public Response<String> addDoctorDetails(@RequestBody Doctors doctor) {
		doctor.setActive(true);
		return userDAL.addDoctorsDetails(doctor);

	}
	
	@RequestMapping(value = "/update-doctors-details", method = RequestMethod.PUT)
	public Response<String> updateDoctorDetails(@RequestBody Doctors doctor) {
		return userDAL.updateDoctorDetails(doctor);}
		
	@RequestMapping(value = "/delete-doctors-details", method = RequestMethod.DELETE)
		public Response<String> deleteDoctorDetails(@RequestBody Doctors doctor) {
			return userDAL.deleteDoctorDetails(doctor);

	}
	
	@RequestMapping(value = "/delete-pharmacy-details", method = RequestMethod.DELETE)
	public Response<String> deletePharmacyDetails(@RequestBody Pharmacy doctor) {
		return userDAL.deletePharmacyDetails(doctor);

}
	
	@RequestMapping(value = "/fetch-doctors-details", method = RequestMethod.GET)
	public Response<List<Doctors>> fetchDoctorsDetails() {
		return userDAL.fetchDoctorsDetails();
	}

	@RequestMapping(value = "/add-pharmacy-details", method = RequestMethod.POST)
	public Response<String> addPharmacyDetails(@RequestBody Pharmacy pharmacy) {
		
		pharmacy.setActive(true);
		return userDAL.addPharmacyDetails(pharmacy);

	}
	
	@RequestMapping(value = "/fetch-pharmacy-details", method = RequestMethod.GET)
	public Response<List<Pharmacy>> fetchPharmacyDetails() {
		return userDAL.fetchPharmacyDetails();
	}
	
	@RequestMapping(value = "/update-pharmacy-details", method = RequestMethod.PUT)
	public Response<String> updatePharmacyDetails(@RequestBody Pharmacy pharmacy) {
		return userDAL.updatePharmacyDetails(pharmacy);

	}

	
	@RequestMapping(value = "/fetch-patient-medical-history/{user_id}", method = RequestMethod.GET)
	public Response<HealthHistory> fetchPatientMedicalHistory(@PathVariable("user_id") long user_id ) {
		return userDAL.fetchPatientMedicalHistory(user_id);
	}
	
	
	
	@RequestMapping(value = "/book-appointment", method = RequestMethod.POST)
	public Response<String> bookAppointment(@RequestBody AppointmentsVO  appointments) {
		Appointments m = new Appointments();
		m.setUserId(appointments.getUserId());
		m.setDoctorId(appointments.getDoctorId());
		m.setAppointmentBookedDate(new Date());
		m.setAppointmentDate(java.sql.Date.valueOf( LocalDate.parse(appointments.getAppointmentDate())));
		return userDAL.bookAppointment(m);
	}
	
	@RequestMapping(value = "/fetch-medical-history", method = RequestMethod.GET)
	public Response<HashMap> fetchMedicalHistory() {
		return userDAL.fetchMedicalHistory();
	}
	
	
	@RequestMapping(value = "/medical-appointment", method = RequestMethod.POST)
	public Response<String> bookAppointment(@RequestBody MedicationsVO appointments  ) {
		Medications m = new Medications();
		m.setUserId(appointments.getUserId());
		m.setPharmacyId(appointments.getPharmacyId());
		m.setBookedDate(new Date());
		m.setEstimatedDeliveryDate(java.sql.Date.valueOf( LocalDate.parse(appointments.getEstimatedDeliveryDate())));
		return userDAL.bookPharmacyAppointment(m);
	}
	
	
	
	
//	@RequestMapping(value = "/upload-prescription", method = RequestMethod.POST)
//	public Response<String> uploadPrescription(@RequestBody Medications  appointments) {
//		return userDAL.uploadPrescription(appointments);
//	}
}




