package util;

import java.util.List;
import java.util.Random;

public class SuiJiShu {
	/**
	 * 用于生成随机数即幸运号码
	 * @param maxnum
	 * @param sl
	 * @return
	 */
	public static int getSuijishu(int maxnum, List<String> sl){
		Random random = new Random();
		int suijishu = random.nextInt(maxnum);	//随机产生一个大于0小于maxnum的值
		suijishu = 1000001+suijishu;
		int i = 0;
		boolean flag = false;
		for (i = 0; i < sl.size(); i++) {
			
			if(suijishu==Integer.valueOf(sl.get(i))){
				flag=true;									//该随机数已经存在
			}
		}
		if(flag){
			suijishu = getSuijishu(maxnum, sl);				//重新生成随机数
		}
		return suijishu;
	}
}
