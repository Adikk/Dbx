package timer;

import dropbox.MainFrame;

public class TimerService {

	Timer timer;
	
	MainFrame okno;
	
	public TimerService(MainFrame okno){
		this.okno = okno;
	}
	
	public void inicjujTimer(){
		timer = new Timer(okno);
		timer.wystartujTimer();
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public MainFrame getOkno() {
		return okno;
	}

	public void setOkno(MainFrame okno) {
		this.okno = okno;
	}
	
	
}
