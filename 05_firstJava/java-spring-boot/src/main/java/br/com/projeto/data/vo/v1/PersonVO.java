package br.com.projeto.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id", "firstName"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable{

	
		private static final long serialVersionUID = 1L;
		
		@JsonProperty("id")
		@Mapping("id")
		private Long key;
		
		@JsonProperty("first_name")
		private String firstName;
		
		private String lastName;

		private String gender;
		
		private String endereco;

		
		public PersonVO() {
		}

		public Long getKey() {
			return key;
		}

		public void setKey(Long key) {
			this.key = key;
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + Objects.hash(endereco, firstName, gender, key, lastName);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			PersonVO other = (PersonVO) obj;
			return Objects.equals(endereco, other.endereco) && Objects.equals(firstName, other.firstName)
					&& Objects.equals(gender, other.gender) && Objects.equals(key, other.key)
					&& Objects.equals(lastName, other.lastName);
		}





}
