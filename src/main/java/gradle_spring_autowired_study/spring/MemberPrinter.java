package gradle_spring_autowired_study.spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {

	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}

	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf("(null)회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
		} else {
			System.out.printf("(not null)회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
					member.getId(), member.getEmail(), member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	
	// AppCtx에 DateTimeFormatter Bean이 없기 때문에... null이 들어가게 된다.
	// 생성자에서 데이트타임포맷 객체를 참조했었어도, null로 set되게 된다.
	@Autowired(required=false)
	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
