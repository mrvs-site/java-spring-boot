package br.com.projeto.data.vo.v2;

import java.io.Serializable;
import java.util.Date;

public class PersonVOV2 implements Serializable{

	
		private static final long serialVersionUID = 1L;

		private Long id;
		private String firstName;
		private String lastName;
		private String gender;
		private String endereco;
		private Date bith;

		
		public PersonVOV2() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public Date getBith() {
			return bith;
		}

		public void setBith(Date bith) {
			this.bith = bith;
		}


}
