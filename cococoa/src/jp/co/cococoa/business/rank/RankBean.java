package jp.co.cococoa.business.rank;

import java.util.List;

public class RankBean {
    private List<RankUserBean> rankUserBeanList;

	public List<RankUserBean> getRankUserBeanList() {
		return rankUserBeanList;
	}

	public void setRankUserBeanList(List<RankUserBean> rankUserBeanList) {
		this.rankUserBeanList = rankUserBeanList;
	}
	public void addRankUserBeanList(RankUserBean rankBean) {
		this.rankUserBeanList.add(rankBean);
	}
}
