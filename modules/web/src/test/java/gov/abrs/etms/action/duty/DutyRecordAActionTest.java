package gov.abrs.etms.action.duty;

import static org.junit.Assert.*;
import gov.abrs.etms.action.BaseActionTest;
import gov.abrs.etms.model.duty.DutyRecordA;
import gov.abrs.etms.service.duty.DutyRecordService;
import gov.abrs.etms.service.util.UtilService;

import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class DutyRecordAActionTest extends BaseActionTest {
	private DutyRecordAAction dutyRecordAAction;
	private DutyRecordService dutyRecordService;
	private UtilService utilService;

	@Before
	public void setup() {
		dutyRecordAAction = new DutyRecordAAction();
		utilService = control.createMock(UtilService.class);
		dutyRecordService = control.createMock(DutyRecordService.class);
		dutyRecordAAction.setDutyRecordService(dutyRecordService);
		dutyRecordAAction.setUtilService(utilService);
	}

	@Test
	public void testSave() {
		Date date = new Date();
		DutyRecordA model = new DutyRecordA();
		model.setId(1L);

		dutyRecordAAction.setModel(model);

		EasyMock.expect(utilService.getSysTime()).andReturn(date);
		this.dutyRecordService.save(model);
		control.replay();

		String result = dutyRecordAAction.save();
		assertEquals(result, dutyRecordAAction.EASY);
	}
}
