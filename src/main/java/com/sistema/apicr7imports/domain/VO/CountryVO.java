package com.sistema.apicr7imports.domain.VO;

import java.io.Serializable;

public class CountryVO implements Serializable {


		private static final long serialVersionUID = 1L;
		private Long id;
		private String namePort;
		private String nameEng;


		/**
		 * 
		 */
		public CountryVO() {

		}
		
		

		/**
		 * @param id
		 */
		public CountryVO(Long id) {
			this.id = id;
		}


		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * @return the namePort
		 */
		public String getNamePort() {
			return namePort;
		}

		/**
		 * @param namePort the namePort to set
		 */
		public void setNamePort(String namePort) {
			this.namePort = namePort;
		}

		/**
		 * @return the nameEng
		 */
		public String getNameEng() {
			return nameEng;
		}

		/**
		 * @param nameEng the nameEng to set
		 */
		public void setNameEng(String nameEng) {
			this.nameEng = nameEng;
		}
}
