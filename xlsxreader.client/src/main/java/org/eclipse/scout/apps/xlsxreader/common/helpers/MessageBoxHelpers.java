package org.eclipse.scout.apps.xlsxreader.common.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.status.IStatus;


public class MessageBoxHelpers {
	
	private static List<String> msg;

	public List<String> getMsg() {
		return msg;
	}

	public void setMsg(List<String> msg) {
		this.msg =  msg;
	}
	
	public static int importMessage() {
		String message = "";
		for(int i = 0; i < msg.size(); i++) {
			message += msg.get(i) + "\n";
		}
		int result = MessageBoxes.create().
				withHeader(message).
				withSeverity(IStatus.INFO)
				.withAutoCloseMillis(5000)
				.show();

		return result;
	}

}
