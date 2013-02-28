package com.octo.training.object;

public class CV {

    private Long intId;
    private Long nivCode;
    private Integer cuvPourcentFormation;

    public CV() {
	}


	public void setIntId(Long intId) {
		this.intId = intId;
	}

	public void setNivCode(Long nivCode) {
		this.nivCode = nivCode;
	}

	public void setCuvPourcentFormation(Integer decimal) {
		cuvPourcentFormation = decimal;
	}
}