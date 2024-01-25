package com.healthcare.jpaImpl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.healthcare.HealthCarePortalApplication;
import com.healthcare.jpa.UserJPA;
import com.healthcare.model.Appointments;
import com.healthcare.model.Doctors;
import com.healthcare.model.HealthHistory;
import com.healthcare.model.Medications;
import com.healthcare.model.Pharmacy;
import com.healthcare.model.Users;
import com.healthcare.repository.AppointmentsRepository;
import com.healthcare.repository.DoctorRepository;
import com.healthcare.repository.HealthHistoryRepository;
import com.healthcare.repository.MedicationsRepository;
import com.healthcare.repository.PharmacyRepository;
import com.healthcare.repository.UserRepository;
import com.healthcare.vo.Response;

@Repository
public class UserJPAImpl implements UserJPA {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HealthHistoryRepository healthHistoryRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;

	
	@Autowired 
	private AppointmentsRepository appointmentsRepository;
	
	@Autowired 
	private PharmacyRepository pharmacyRepository;
	
	@Autowired 
	private MedicationsRepository medicationsRepository;
	
	
	

	@Transactional
	@Override
	public Response<String> addUser(Users user) {
		Response<String> response = new Response<String>();
		try {
			user = userRepository.save(user);
			response.setStatus("Success");
			response.setMessage("User Saved Successfully");

		} catch (Exception e) {
			//healthCarePortalApplication.printMsg();
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;
	}

	@Override
	public Response<Users> userLogin(Users user) {
		Response<Users> response = new Response<Users>();
		try {
			Users fetchedUser = userRepository.findFirstByUserName(user.getUserName());
			if (fetchedUser != null) {
				if (fetchedUser.getPassword().contentEquals(user.getPassword())) {
					response.setData(fetchedUser);
					response.setStatus("Success");
					response.setMessage("User Logged in Successfully");
				} else {
					response.setStatus("Failed");
					response.setMessage("Invalid username or Password");
				}

			} else {
				response.setStatus("Failed");
				response.setMessage("User Not Found");
			}
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
	}

	@Override
	public Response<String> addHealthHistory(HealthHistory healthHistory) {

		Response<String> response = new Response<String>();
		try {
			healthHistoryRepository.save(healthHistory);
			response.setStatus("Success");
			response.setMessage("Medical history Saved Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
	}

	@Override
	public Response<String> addDoctors(Doctors doctor) {
		Response<String> response = new Response<String>();
		try {
			doctorRepository.save(doctor);
			response.setStatus("Success");
			response.setMessage("Doctor Saved Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
	}

	@Override
	public Response<Users> fetchUser(long userId) {
		Response<Users> response = new Response<Users>();
		try {
			Users user = userRepository.findFirstByUserId(userId);
			response.setStatus("Success");
			response.setData(user);
			response.setMessage("User data fetched Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
	}
	
	

	@Override
	public Response<List<Doctors>> fetchDoctors() {
		Response<List<Doctors>> response = new Response<List<Doctors>>();
		try {
			List<Doctors> doctors = doctorRepository.findByIsActive(true);
			response.setStatus("Success");
			response.setData(doctors);
			response.setMessage("doctors data fetched Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;

	}

	@Override
	public Response<HealthHistory> fetchPatientMedicalHistory(long user_id) {
		Response<HealthHistory> response = new Response<HealthHistory>();
		try {
			
			HealthHistory healthHistory = healthHistoryRepository.findFirstByUserIdOrderByRecordedAtDesc(user_id);
			response.setStatus("Success");
			response.setData(healthHistory);
			response.setMessage("Medical data fetched Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;

	}
	
	@Override
	public Response<List<HealthHistory>> fetchPatientMedicalHistoryAll(long userid) {
		Response<List<HealthHistory>> response = new Response<List<HealthHistory>>();
		try {
			
			List<HealthHistory> healthHistory = healthHistoryRepository.findByUserId(userid);
			response.setStatus("Success");
			response.setData(healthHistory);
			response.setMessage("Medical data fetched Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;

	}

	@Override
	public Response<String> updateDoctors(Doctors doctor) {
		Response<String> response = new Response<String>();
		try {
			doctorRepository.save(doctor);
			response.setStatus("Success");
			response.setMessage("doctors data updated Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;

	}
	
	@Override
	public Doctors fetchDoctors(long id) {
		Doctors doctors = new Doctors();
		try {
			 doctors = doctorRepository.findById(id);
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return doctors;

	}
	
	@Override
	public Pharmacy fetchPharmacy(long id) {
		Pharmacy pharma = new Pharmacy();
		try {
			pharma = pharmacyRepository.findById(id);
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return pharma;
	}

	@Override
	public Response<String> bookAppointment(Appointments appointments) {
		Response<String> response = new Response<String>();
		try {
			appointmentsRepository.save(appointments);
			response.setStatus("Success");
			response.setMessage("Appointment scheduled Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured while scheduling appointment");
		}
		return response;

	}

	@Override
	public Response<String> addPharmacy(Pharmacy pharmacy) {
		Response<String> response = new Response<String>();
		try {
			pharmacyRepository.save(pharmacy);
			response.setStatus("Success");
			response.setMessage("Pharmacy data Saved Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
	}

	@Override
	public Response<List<Pharmacy>> fetchPharmacyDetails() {
		Response<List<Pharmacy>> response = new Response<List<Pharmacy>>();
		try {
			List<Pharmacy> pharmacy = pharmacyRepository.findByIsActive(true);
			response.setStatus("Success");
			response.setData(pharmacy);
			response.setMessage("Pharmacy data fetched Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;

	}
	
	@Override
	public Response<String> updatePharmacy(Pharmacy pharmacy ) {
		Response<String> response = new Response<String>();
		try {
			pharmacyRepository.save(pharmacy);
			response.setStatus("Success");
			response.setMessage("Pharmacy data updated Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;

	}

	@Override
	public Response<List<Users>> fetchUsers() {
		Response<List<Users>> response = new Response<List<Users>>();
		try {
			List<Users> user = userRepository.findAll();
			response.setStatus("Success");
			response.setData(user);
			response.setMessage("User data fetched Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
	}

	@Override
	public Response<String> deleteDoctorDetails(Doctors doc) {
		Response<String> response = new Response<String>();
		try {
			doctorRepository.save(doc);
			response.setStatus("Success");
			response.setMessage("Doctor data deleted Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
}

	@Override
	public Response<String> deleteDoctorDetails(long l) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<String> bookMedicalAppointment(Medications appointments) {
		Response<String> response = new Response<String>();
		try {
			medicationsRepository.save(appointments);
			response.setStatus("Success");
			response.setMessage("Medical Appointment scheduled Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured while scheduling appointment");
		}
		return response;

	}

	@Override
	public Response<String> deletePharmacyDetails(Pharmacy pharmacy) {
		Response<String> response = new Response<String>();
		try {
			pharmacyRepository.save(pharmacy);
			response.setStatus("Success");
			response.setMessage("Pharmacy data deleted Successfully");

		} catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
	}
	
	
	}
