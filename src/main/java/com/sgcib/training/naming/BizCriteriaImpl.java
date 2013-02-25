package com.sgcib.training.naming;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BizCriteriaImpl {

	private final static Log logger = LogFactory.getLog(BizCriteriaImpl.class);

	public static void checkCanLoadCriteria() {
		List<Integer> agencyIds = ThreadContext.getContext().getUserAgencyIds();
		if (agencyIds.contains(IExtranetBoConstants.ID_INT)) {
			BusinessRuleException.throwStandardBusinessException(ErrorMessage.getString(
					"user_hasnt_load_user"), logger);
		}
	}

	public static void filterAdvertiserByUserRights(List<DomAdvertiser> advertisers) {
		if (advertisers != null && !advertisers.isEmpty()) {
			ThreadContext context = ThreadContext.getContext();
			List<Integer> abilities = context.getUserAbilities();

			if (abilities != null && !abilities.isEmpty()) {
				String advertiserCode;
				Integer abilitieCode;
				boolean hasToRemove;
				for (int i = 0; i < advertisers.size(); i++) {
					hasToRemove = true;
					advertiserCode = advertisers.get(i).getCode();
					for (int j = 0; j < abilities.size(); j++) {
						abilitieCode = abilities.get(j);

						if ((abilitieCode.toString()).equals(advertiserCode)) {
							hasToRemove = false;
							break;
						}
					}
					if (hasToRemove) {
						advertisers.remove(i);
						i--;
					}
				}
			}
		}
	}
}