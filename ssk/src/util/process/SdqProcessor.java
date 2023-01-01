package util.process;

import java.util.ArrayList;

import model.dto.SdqReply;

public class SdqProcessor {
	public static int[] getSdqReplyListToSdqResult(ArrayList<SdqReply> sdqReplyList) {
		int[] scoreList = new int[5];
		
		for(int i=0;i<sdqReplyList.size();i++) {
			int QNUM = sdqReplyList.get(i).getSdqQuestionId()%10;
			if(QNUM==1 ||QNUM == 2) {//사회 지향 행동
				scoreList[0]+=sdqReplyList.get(i).getSdqReplyContent();
			}else if(QNUM==3||QNUM==4) {//과잉행동
				scoreList[1]+=sdqReplyList.get(i).getSdqReplyContent();
			}else if(QNUM==5||QNUM==6) {//정서 증상
				scoreList[2]+=sdqReplyList.get(i).getSdqReplyContent();
			}else if(QNUM==7||QNUM==8) {//품행문제
				scoreList[3]+=sdqReplyList.get(i).getSdqReplyContent();
			}else if(QNUM==9||QNUM==10) {//또래문제
				scoreList[4]+=sdqReplyList.get(i).getSdqReplyContent();
			}
		}
		
		return scoreList;
	}

}
