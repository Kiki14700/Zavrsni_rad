package org.eclipse.scout.apps.xlsxreader.aboutclients;

import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.CancelButton;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.OkButton;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderCancelButton;
import org.eclipse.scout.apps.xlsxreader.buttons.AbstractXLSXReaderOKButton;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.BasicInfoBox;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.BasicInfoBox.NameField;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.BasicInfoBox.SurnameField;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.BasicInfoBox.OIBField;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.BasicInfoBox.DateOfBirthField;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.ResidenceBox;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.ResidenceBox.AddressField;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.ResidenceBox.TownField;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.ContactBox;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.ContactBox.EmailField;
import org.eclipse.scout.apps.xlsxreader.aboutclients.ClientForm.MainBox.GroupBox.ContactBox.PhoneField;

@FormData(value = ClientFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ClientForm extends AbstractForm {

	private Integer userID;
	private String userName;
	private String userSurname;
	private String userCode;

	@FormData
	public Integer getUserID() {
		return userID;
	}

	@FormData
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [Kristian Knezevic] verify translation
		return TEXTS.get("ClientForm.Client");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public BasicInfoBox getBasicInfoBox() {
		return getFieldByClass(BasicInfoBox.class);
	}

	public SurnameField getSurnameField() {
		return getFieldByClass(SurnameField.class);
	}

	public OIBField getOIBField() {
		return getFieldByClass(OIBField.class);
	}

	public DateOfBirthField getDateOfBirthField() {
		return getFieldByClass(DateOfBirthField.class);
	}

	public ResidenceBox getAddressBox() {
		return getFieldByClass(ResidenceBox.class);
	}

	public AddressField getAddressField() {
		return getFieldByClass(AddressField.class);
	}

	public TownField getTownField() {
		return getFieldByClass(TownField.class);
	}

	public ContactBox getContactBox() {
		return getFieldByClass(ContactBox.class);
	}

	public EmailField getEmailField() {
		return getFieldByClass(EmailField.class);
	}

	public PhoneField getPhoneField() {
		return getFieldByClass(PhoneField.class);
	}

	public NameField getNameField() {
		return getFieldByClass(NameField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {
		@Order(1000)
		public class GroupBox extends AbstractGroupBox {

			@Override
			protected int getConfiguredGridColumnCount() {
				return 1;
			}

			@Order(1000)
			public class BasicInfoBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ClientForm.MainBox.GroupBox.BasicInfoBox.BasicInfo");
				}

				@Override
				protected int getConfiguredGridColumnCount() {
					return 2;
				}

				@Order(1000)
				public class NameField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientForm.MainBox.GroupBox.BasicInfoBox.Name");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

				@Order(2000)
				public class OIBField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientForm.MainBox.GroupBox.BasicInfoBox.OIB");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

				@Order(3000)
				public class SurnameField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientForm.MainBox.GroupBox.BasicInfoBox.Surname");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

				@Order(4000)
				public class DateOfBirthField extends AbstractDateField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientForm.MainBox.GroupBox.BasicInfoBox.DateOfBirth");
					}
				}

			}

			@Order(2000)
			public class ResidenceBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ClientForm.MainBox.GroupBox.ResidenceBox.Residence");
				}
				
				@Override
				protected int getConfiguredGridColumnCount() {
					return 2;
				}

				@Order(1000)
				public class AddressField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientForm.MainBox.GroupBox.ResidenceBox.Address");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

				@Order(2000)
				public class TownField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientForm.MainBox.GroupBox.ResidenceBox.Town");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}
				
				
				
				
			}

			@Order(3000)
			public class ContactBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ClientForm.MainBox.GroupBox.ContactBox.Contact");
				}
				
				@Override
				protected int getConfiguredGridColumnCount() {
					return 2;
				}

				@Order(1000)
				public class EmailField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientForm.MainBox.GroupBox.ContactBox.Email");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

				@Order(2000)
				public class PhoneField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("ClientForm.MainBox.GroupBox.ContactBox.Phone");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}
				
				
				
			}
			
			
			
			
			

		}

		@Order(2000)
		public class OkButton extends AbstractXLSXReaderOKButton {

		}

		@Order(3000)
		public class CancelButton extends AbstractXLSXReaderCancelButton {

		}
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public class NewHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			ClientFormData formData = new ClientFormData();
			exportFormData(formData);
			formData = BEANS.get(IClientService.class).prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			ClientFormData formData = new ClientFormData();
			exportFormData(formData);
			formData = BEANS.get(IClientService.class).create(formData);
			importFormData(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			ClientFormData formData = new ClientFormData();
			exportFormData(formData);
			formData = BEANS.get(IClientService.class).load(formData);
			importFormData(formData);

			setTitle(userName + " " + userSurname);
			setSubTitle(userCode);
		}

		@Override
		protected void execStore() {
			ClientFormData formData = new ClientFormData();
			exportFormData(formData);
			formData = BEANS.get(IClientService.class).store(formData);
			importFormData(formData);
		}
	}
}
