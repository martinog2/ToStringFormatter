
public class ToStringFormatter
{

	public static void main(String[] args) 
	{
//		String s = "parties=[ANEPartyDesc [partyName=FCEC, partyType=ORIGINATION_FIRM, aliases=null], ANEPartyDesc [partyName=martin.ogrady@FICACCT3.com, partyType=ORIGINATION_TRADER, aliases=null]], ";
//		String s = "Payload=ANEFIMultiPartyQuoteRequest [ANEMultiPartyQuoteRequest [parties=[ANEPartyDesc [partyName=FCEC, partyType=ORIGINATION_FIRM, aliases=null], ANEPartyDesc [partyName=martin.ogrady@FICACCT3.com, partyType=ORIGINATION_TRADER, aliases=null]], makerDetails=[ANEMakerOrderOverride [legs=[ANEMakerLegOverride [requestedNotionalAmount=12120.0000, legQuote=ANELegQuote [legNum=0, bidQuote=ANELegExecutedQuote [legNum=0, legPrice=12120.1000, legQuotedNotional=12120.0000], askQuote=ANELegExecutedQuote [legNum=0, legPrice=null, legQuotedNotional=12120.0000]]]], makerName=FCDW, quoteRequestId=null, comment=null]], ANEQuoteRequestBase [regInfo=null, requestSide=ONEWAY_CLIENTSELL, quoteStyle=RFQ, recipientType=AUTO_OR_MANUAL, dealDate=null, submissionTime=null, legs=[ANEFILegWithQuote [ ANELegWithQuote [leg=ANELeg [legNum=0, side=SELL, requestedNotionalAmount=null, notionalAmount=null, notionalCurrency=EUR, tenor=null, rate=110.498, regInfo=null, allocations=[ANEAllocationWithExecutedQuote [alloc=ANEAllocation [side=SELL, allocationId=0, requestedNotionalAmount=null, notionalAmount=null, notionalCurrency=USD, account=null, settleInstructions={FCEC=com.tr.utp.ane.pojo.ANESettleInstructions@ee288bdc}], executedQuote=ANELegExecutedQuote [legNum=0, legPrice=null, legQuotedNotional=null], executedSide=null], ANEAllocationWithExecutedQuote [alloc=ANEAllocation [side=SELL, allocationId=1, requestedNotionalAmount=null, notionalAmount=null, notionalCurrency=null, account=acct, settleInstructions={}], executedQuote=ANELegExecutedQuote [legNum=0, legPrice=null, legQuotedNotional=null], executedSide=null]]], quote=ANELegQuote [legNum=0, bidQuote=ANELegExecutedQuote [legNum=0, legPrice=null, legQuotedNotional=null], askQuote=ANELegExecutedQuote [legNum=0, legPrice=null, legQuotedNotional=null]], executedQuote=null, executedSide=null]]]]]]"; 
		String s = "msg=SimplePojoMessage [header=PojoMessageHeader [topicName=Market/1/D/ANE/EUDTC/Callout/FIXEDINCOME/././NEGOTIATION_CANCEL/FICallout_Gateway_02/DTC, senderName=null, endSenderName=null, conversationId=PAFXTTEST-0935_cid, sendToDestinations=false, destinations=null, replyTopic=null, sequenceNumber=null], pojo=ANENegotiationObject [negotiationDesc=ANENegotiationDesc [requestId=ANE201707141231EUDTCFI0000030.865, parentRequestId=ANE201707141231EUDTCFI0000030, eventNum=2, eventSubNum=0, eventSource=SYSTEM, lastQuoteSource=INITIATOR, lastEvent=NEGOTIATION_CANCEL, currentState=TRADE_NOT_DONE, role=INITIATOR, party=FCEC, allowableEvents=null, eventTime=2017-07-16 15:23:03.304, eventSender=FICallout_TimeoutMon_02], parentNegotiationDesc=ANEParentNegotiationDesc [parentRequestId=ANE201707141231EUDTCFI0000030, currentState=COMPLETED, bestBidPrice=null, bestAskPrice=null, eventSource=SYSTEM, lastChildEvent=NEGOTIATION_CANCEL, lastChildId=ANE201707141231EUDTCFI0000030.865], payload=com.tr.utp.ane.pojo.ANEQuoteCancel@f5260066]]";
		
		parse(s.toCharArray(), 1, 0);
	}

	
	static int parse(char[] s, int level, int pos)
	{
		if(pos > 0 && s[pos-1] != ']')
		{
			for(int i=0; i<level; i++) System.out.print("\t");
		}
		
		while(pos < s.length)
		{
			if(s[pos] == '[')
			{
				System.out.print(s[pos]);
				System.out.println();
				pos = parse(s, level+1, pos+1);
				
				if(pos > 0 && s[pos-1] != ']')
				{
					for(int i=0; i<level; i++) System.out.print("\t");
				}
			}
			else if(s[pos] == ']' && pos+1 < s.length && s[pos+1]==',' && pos+2 < s.length && s[pos+2]==' ')
			{
				System.out.println();
				for(int i=0; i<level-1; i++) System.out.print("\t");
				System.out.print(s[pos]);
				System.out.print(s[pos+1]);
				System.out.println(s[pos+2]);
				return pos+3;
			}
			
			else if(s[pos] == ']')
			{
				System.out.println();
				for(int i=0; i<level-1; i++) System.out.print("\t");
				System.out.print(s[pos]);
				return pos+1;
			}
			else if(s[pos] == ',' && pos+1 < s.length && s[pos+1]==' ')
			{
				System.out.print(s[pos]);
				System.out.println(s[pos+1]);
				for(int i=0; i<level; i++) System.out.print("\t");
				pos += 2;
			}
			else
			{
				System.out.print(s[pos]);
				pos++;
			}
			
		}
		
		return pos;
	}
}
