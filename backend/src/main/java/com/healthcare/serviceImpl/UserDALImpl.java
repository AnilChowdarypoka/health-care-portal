package com.healthcare.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.jpa.UserJPA;
import com.healthcare.model.Appointments;
import com.healthcare.model.Doctors;
import com.healthcare.model.HealthHistory;
import com.healthcare.model.Medications;
import com.healthcare.model.Pharmacy;
import com.healthcare.model.Users;
import com.healthcare.service.UserDAL;
import com.healthcare.vo.Response;

@Component
public class UserDALImpl implements UserDAL {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserJPA userJPA;
	
	

	@Override
	public Response<Users> userLogin(Users user) {
		Response<Users> response = new Response<Users>();
		try {
			response = userJPA.userLogin(user);
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> addUser(Users user) {
		Response<String> response = new Response<String>();
		try {
			if (user.getCountry().contentEquals("India") || user.getCountry().contentEquals("USA")) {

				Date date = new Date();

				long age = (date.getTime() - user.getDob().getTime()) / (24 * 1000L * 60 * 60 * 365);

				if (age > 18 && age <= 60) {
					if (user.getRole() == null)
						user.setRole("user");
					if (user.getCreateAt() == null) {
						user.setCreateAt(new Date());
					}
					response = userJPA.addUser(user);
				} else {
					response.setStatus("Failed");
					response.setMessage("Customer's age is not in between 18 and 60.");
				}

			} else {
				response.setStatus("Failed");
				response.setMessage("Customer is not from India/USA");
			}
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> addHealthHistory(HealthHistory healthHistory) {
		Response<String> response = new Response<String>();
		try {
			if (healthHistory.getRecordedAt() == null) {
				healthHistory.setRecordedAt(new Date());
			}
			response = userJPA.addHealthHistory(healthHistory);
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> addDoctorsDetails(Doctors doctor) {
		Response<String> response = new Response<String>();
		try {
			Response<Users> userResponse = fetchuserdetails(doctor.getAddedBy());
			if (userResponse.getStatus() == "Success" && userResponse.getData() != null) {
				Users user = userResponse.getData();
				if (user.getRole().contentEquals("admin")) {
					if (doctor.getCreatedAt() == null) {
						doctor.setCreatedAt(new Date());
					}
					response = userJPA.addDoctors(doctor);
				} else {

					response.setStatus("Failed");
					response.setMessage("Only admin can add doctor details");
				}
			} else {
				response.setStatus("Failed");
				response.setMessage("Failed to fetch added user details");
			}
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	public Response<Users> fetchuserdetails(long userId) {
		Response<Users> response = new Response<Users>();
		try {
			response = userJPA.fetchUser(userId);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<List<Doctors>> fetchDoctorsDetails() {
		Response<List<Doctors>> response = new Response<List<Doctors>>();

		try {
			response = userJPA.fetchDoctors();

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<HealthHistory> fetchPatientMedicalHistory(long user_id) {
		Response<HealthHistory> response = new Response<HealthHistory>();

		try {
			response = userJPA.fetchPatientMedicalHistory(user_id);
			if (response.getStatus().contentEquals("Success")) {
				if (response.getData() != null)
					return response;
				else {
					response.setStatus("Success");
					response.setMessage("No Medical history Available for said user");
				}
			} else {

				response.setStatus("Failed");
				response.setMessage("Failed to fetch medical history of said user");
			}

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> updateDoctorDetails(Doctors doctor) {
		Response<String> response = new Response<String>();
		try {
			Response<Users> userResponse = fetchuserdetails(doctor.getAddedBy());
			if (userResponse.getStatus() == "Success" && userResponse.getData() != null) {
				Users user = userResponse.getData();
				if (user.getRole().contentEquals("admin")) {

					Doctors doc = userJPA.fetchDoctors(doctor.getDoctorId());
					if (doc != null) {
						doctor.setDoctorId(doc.getDoctorId());
						doctor.setCreatedAt(doc.getCreatedAt());

						response = userJPA.updateDoctors(doctor);
					} else {
						response.setStatus("Failed");
						response.setMessage("No doctor data available for said doctor id");
					}
				} else {

					response.setStatus("Failed");
					response.setMessage("Only admin can update doctor details");
				}
			} else {
				response.setStatus("Failed");
				response.setMessage("Failed to fetch update user details");
			}
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> bookAppointment(Appointments appointments) {
		Response<String> response = new Response<String>();

		try {

			if (appointments.getAppointmentBookedDate() == null) {
				appointments.setAppointmentBookedDate(new Date());
			}
			response = userJPA.bookAppointment(appointments);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> addPharmacyDetails(Pharmacy pharmacy) {
		Response<String> response = new Response<String>();
		try {
			Response<Users> userResponse = fetchuserdetails(pharmacy.getAddedBy());
			System.out.println(userResponse);
			if (userResponse.getStatus() == "Success" && userResponse.getData() != null) {
				Users user = userResponse.getData();
				if (user.getRole().contentEquals("admin")) {
					if (pharmacy.getCreatedAt() == null) {
						pharmacy.setCreatedAt(new Date());
					}
					response = userJPA.addPharmacy(pharmacy);
				} else {

					response.setStatus("Failed");
					response.setMessage("Only admin can add doctor details");
				}
			} else {
				response.setStatus("Failed");
				response.setMessage("Failed to fetch added user details");
			}
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<List<Pharmacy>> fetchPharmacyDetails() {

		Response<List<Pharmacy>> response = new Response<List<Pharmacy>>();

		try {
			response = userJPA.fetchPharmacyDetails();

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> updatePharmacyDetails(Pharmacy pharmacy) {
		Response<String> response = new Response<String>();
		try {
			Response<Users> userResponse = fetchuserdetails(pharmacy.getAddedBy());
			if (userResponse.getStatus() == "Success" && userResponse.getData() != null) {
				Users user = userResponse.getData();
				if (user.getRole().contentEquals("admin")) {

					Pharmacy Pharma = userJPA.fetchPharmacy(pharmacy.getPharmacyId());
					if (Pharma != null) {
						pharmacy.setPharmacyId(Pharma.getPharmacyId());
						pharmacy.setCreatedAt(Pharma.getCreatedAt());

						response = userJPA.updatePharmacy(pharmacy);
					} else {
						response.setStatus("Failed");
						response.setMessage("No doctor data available for said doctor id");
					}
				} else {

					response.setStatus("Failed");
					response.setMessage("Only admin can update doctor details");
				}
			} else {
				response.setStatus("Failed");
				response.setMessage("Failed to fetch update user details");
			}
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<HashMap> fetchMedicalHistory() {
		Response<HashMap> response = new Response<HashMap>();

		try {
			HashMap<String , List> hs = new HashMap<String , List>();
			Response<List<Users>> userResponse = new Response<List<Users>>();
			userResponse = userJPA.fetchUsers();
			if (userResponse.getStatus() == "Success" && userResponse.getData() != null) {
				List<Users>  users =  userResponse.getData();
				for (Users u : users) {
					Response<List<HealthHistory>> healthResponse = userJPA.fetchPatientMedicalHistoryAll(u.getUserId());
					if (userResponse.getStatus() == "Success") {
						hs.put(u.getUserName(), healthResponse.getData() );
					}
					else {
						throw new Exception("Failed to fetch medical history");
					}
				}
				
			}
			else {
				response.setStatus("Failed");
				response.setMessage("Failed to fetch user details");
			}
			response.setStatus("Success");
			response.setMessage("Sucessfully fetched data");
			response.setData(hs);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> deleteDoctorDetails(Doctors doctor) {
		Response<String> response = new Response<String>();

		try {
			log.info("Deleting doctor data");
			doctor.setActive(false);
			response = userJPA.deleteDoctorDetails(doctor);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<String> bookPharmacyAppointment(Medications appointments) {
		Response<String> response = new Response<String>();

		try {
			
			System.out.println(appointments.getEstimatedDeliveryDate());

			if (appointments.getBookedDate() == null) {
				appointments.setBookedDate(new Date());
			}
			response = userJPA.bookMedicalAppointment(appointments);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}
	
	
	

	@Override
	public Response<String> deletePharmacyDetails(Pharmacy pharmacy) {
		Response<String> response = new Response<String>();

		try {
			log.info("Deleting pharmacy data");
			pharmacy.setActive(false);
			response = userJPA.deletePharmacyDetails(pharmacy);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}
}
