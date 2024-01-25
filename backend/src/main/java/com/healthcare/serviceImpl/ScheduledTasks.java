package com.healthcare.serviceImpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.healthcare.jpa.UserJPA;
import com.healthcare.model.Appointments;
import com.healthcare.model.Doctors;
import com.healthcare.model.EmailDetails;
import com.healthcare.model.Medications;
import com.healthcare.model.Pharmacy;
import com.healthcare.model.Users;
import com.healthcare.repository.AppointmentsRepository;
import com.healthcare.repository.MedicationsRepository;
import com.healthcare.service.EmailService;
import com.healthcare.vo.Response;

@Component
public class ScheduledTasks {

	@Autowired
	private AppointmentsRepository appointmentsRepository;
	
	@Autowired
	private MedicationsRepository medicationsRepository;


	@Autowired
	private UserJPA userJPA;

	@Autowired
	private EmailService emailService;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	// private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	//@Scheduled(fixedRate = 5000)
	public void sendRemainders() {
		List<Appointments> appointments = appointmentsRepository.findAllByAppointmentDate(new Date());
		log.info(Integer.toString(appointments.size()));
		for (Appointments i : appointments) {
			Response<Users> userResponse = userJPA.fetchUser(i.getUserId());
			
			if (userResponse.getStatus().contentEquals("Success")) {
				Users user = userResponse.getData();
				Doctors doctor = userJPA.fetchDoctors( i.getDoctorId()) ;
				String subject = "Remainder for an appointment with Dr." + doctor.getDoctorName() +" !!!!";
				String email_body = "Hey " + user.getFullName() + ",\n\n\n" + "You have an appoinment with Dr." +  doctor.getDoctorName() + " at our hospital.\n\n Please reach on time with all necessary documents. \n\n\n Thanks,\n Team Health Care." ;
				String response = emailService
						.sendSimpleMail(new EmailDetails(user.getEmailId(), subject, email_body));
				log.info(response);
			}
		}
	}
	
	
	@Scheduled(fixedRate = 5000)
	public void sendRemaindersMedical() {
		List<Medications> medications = medicationsRepository.findAllByEstimatedDeliveryDate(new Date());
		log.info("MeDICATIONS SIZE " + Integer.toString(medications.size()));
		for (Medications i : medications) {
			Response<Users> userResponse = userJPA.fetchUser(i.getUserId());
			
			if (userResponse.getStatus().contentEquals("Success")) {
				Users user = userResponse.getData();
				Pharmacy doctor = userJPA.fetchPharmacy( i.getPharmacyId()) ;
				String subject = "Remainder for an appointment with Dr." + doctor.getPharmacyName() +" !!!!";
				String email_body = "Hey " + user.getFullName() + ",\n\n\n" + "You medications are ready to deliver. Please do visit " +  doctor.getPharmacyName() + " store and collect all the medicines and documents. \n\n\n Thanks,\n Team Health Care." ;
				String response = emailService
						.sendSimpleMail(new EmailDetails(user.getEmailId(), subject, email_body));
				log.info(response);
			}
		}
	}

}
