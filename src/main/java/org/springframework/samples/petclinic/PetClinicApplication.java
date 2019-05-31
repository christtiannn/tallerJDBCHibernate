/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.samples.petclinic.vet.SpecialtyRepositoryJPA;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepositoryJPA;

/**
 * PetClinic Spring Boot Application.
 * 
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication implements CommandLineRunner {

	@Autowired
	VetRepositoryJPA vetrepositJpa;
	@Autowired
	SpecialtyRepositoryJPA spec;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PetClinicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Vet vt = new Vet();
		vt.setFirstName("Benito");
		vt.setLastName("Perez");
		vt = vetrepositJpa.save(vt);
		if (vt.getId() > 0) {
			Specialty sp = new Specialty();
			sp.setName("perritos");
			sp = spec.save(sp);
			Specialty sp1 = spec.findOne(1); // Selecciono especialidad por id
			vt.addSpecialty(sp1); // La añado al Vet
			vetrepositJpa.save(vt); // Guardo Vet
		} else {
			System.out.println("Error");
		}

		List<Vet> allVet = vetrepositJpa.findAll();
		allVet.hashCode();//Pa' na'.
	}

}
