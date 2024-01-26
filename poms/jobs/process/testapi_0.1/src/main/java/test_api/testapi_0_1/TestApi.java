// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package test_api.testapi_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: TestApi Purpose: fgfgf<br>
 * Description: egrgrezgr <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class TestApi implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "TestApi";
	private final String projectName = "TEST_API";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					TestApi.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(TestApi.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tRESTClient_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tExtractJSONFields_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tReplicate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_TEST_API_TestApi = new byte[0];
		static byte[] commonByteArray_TEST_API_TestApi = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int IdDrink;

		public int getIdDrink() {
			return this.IdDrink;
		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public String Category;

		public String getCategory() {
			return this.Category;
		}

		public String Alcoholic;

		public String getAlcoholic() {
			return this.Alcoholic;
		}

		public String Glass;

		public String getGlass() {
			return this.Glass;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Image;

		public String getImage() {
			return this.Image;
		}

		public List IngredientList;

		public List getIngredientList() {
			return this.IngredientList;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.IdDrink;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row5Struct other = (row5Struct) obj;

			if (this.IdDrink != other.IdDrink)
				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.IdDrink = this.IdDrink;
			other.Name = this.Name;
			other.Category = this.Category;
			other.Alcoholic = this.Alcoholic;
			other.Glass = this.Glass;
			other.Instructions = this.Instructions;
			other.Image = this.Image;
			other.IngredientList = this.IngredientList;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.IdDrink = this.IdDrink;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.IngredientList = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.IngredientList = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.IngredientList);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.IngredientList);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IdDrink=" + String.valueOf(IdDrink));
			sb.append(",Name=" + Name);
			sb.append(",Category=" + Category);
			sb.append(",Alcoholic=" + Alcoholic);
			sb.append(",Glass=" + Glass);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Image=" + Image);
			sb.append(",IngredientList=" + String.valueOf(IngredientList));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IdDrink, other.IdDrink);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_TEST_API_TestApi = new byte[0];
		static byte[] commonByteArray_TEST_API_TestApi = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int IdDrink;

		public int getIdDrink() {
			return this.IdDrink;
		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public String Category;

		public String getCategory() {
			return this.Category;
		}

		public String Alcoholic;

		public String getAlcoholic() {
			return this.Alcoholic;
		}

		public String Glass;

		public String getGlass() {
			return this.Glass;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Image;

		public String getImage() {
			return this.Image;
		}

		public List IngredientList;

		public List getIngredientList() {
			return this.IngredientList;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.IdDrink;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row6Struct other = (row6Struct) obj;

			if (this.IdDrink != other.IdDrink)
				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

			other.IdDrink = this.IdDrink;
			other.Name = this.Name;
			other.Category = this.Category;
			other.Alcoholic = this.Alcoholic;
			other.Glass = this.Glass;
			other.Instructions = this.Instructions;
			other.Image = this.Image;
			other.IngredientList = this.IngredientList;

		}

		public void copyKeysDataTo(row6Struct other) {

			other.IdDrink = this.IdDrink;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.IngredientList = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.IngredientList = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.IngredientList);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.IngredientList);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IdDrink=" + String.valueOf(IdDrink));
			sb.append(",Name=" + Name);
			sb.append(",Category=" + Category);
			sb.append(",Alcoholic=" + Alcoholic);
			sb.append(",Glass=" + Glass);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Image=" + Image);
			sb.append(",IngredientList=" + String.valueOf(IngredientList));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IdDrink, other.IdDrink);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_TEST_API_TestApi = new byte[0];
		static byte[] commonByteArray_TEST_API_TestApi = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int IdDrink;

		public int getIdDrink() {
			return this.IdDrink;
		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public String Category;

		public String getCategory() {
			return this.Category;
		}

		public String Alcoholic;

		public String getAlcoholic() {
			return this.Alcoholic;
		}

		public String Glass;

		public String getGlass() {
			return this.Glass;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Image;

		public String getImage() {
			return this.Image;
		}

		public List IngredientList;

		public List getIngredientList() {
			return this.IngredientList;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.IdDrink;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row4Struct other = (row4Struct) obj;

			if (this.IdDrink != other.IdDrink)
				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.IdDrink = this.IdDrink;
			other.Name = this.Name;
			other.Category = this.Category;
			other.Alcoholic = this.Alcoholic;
			other.Glass = this.Glass;
			other.Instructions = this.Instructions;
			other.Image = this.Image;
			other.IngredientList = this.IngredientList;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.IdDrink = this.IdDrink;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.IngredientList = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.IngredientList = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.IngredientList);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.IngredientList);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IdDrink=" + String.valueOf(IdDrink));
			sb.append(",Name=" + Name);
			sb.append(",Category=" + Category);
			sb.append(",Alcoholic=" + Alcoholic);
			sb.append(",Glass=" + Glass);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Image=" + Image);
			sb.append(",IngredientList=" + String.valueOf(IngredientList));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IdDrink, other.IdDrink);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class testStruct implements routines.system.IPersistableRow<testStruct> {
		final static byte[] commonByteArrayLock_TEST_API_TestApi = new byte[0];
		static byte[] commonByteArray_TEST_API_TestApi = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int IdDrink;

		public int getIdDrink() {
			return this.IdDrink;
		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public String Category;

		public String getCategory() {
			return this.Category;
		}

		public String Alcoholic;

		public String getAlcoholic() {
			return this.Alcoholic;
		}

		public String Glass;

		public String getGlass() {
			return this.Glass;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Image;

		public String getImage() {
			return this.Image;
		}

		public List IngredientList;

		public List getIngredientList() {
			return this.IngredientList;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.IdDrink;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final testStruct other = (testStruct) obj;

			if (this.IdDrink != other.IdDrink)
				return false;

			return true;
		}

		public void copyDataTo(testStruct other) {

			other.IdDrink = this.IdDrink;
			other.Name = this.Name;
			other.Category = this.Category;
			other.Alcoholic = this.Alcoholic;
			other.Glass = this.Glass;
			other.Instructions = this.Instructions;
			other.Image = this.Image;
			other.IngredientList = this.IngredientList;

		}

		public void copyKeysDataTo(testStruct other) {

			other.IdDrink = this.IdDrink;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.IngredientList = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.IngredientList = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.IngredientList);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.IngredientList);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IdDrink=" + String.valueOf(IdDrink));
			sb.append(",Name=" + Name);
			sb.append(",Category=" + Category);
			sb.append(",Alcoholic=" + Alcoholic);
			sb.append(",Glass=" + Glass);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Image=" + Image);
			sb.append(",IngredientList=" + String.valueOf(IngredientList));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(testStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IdDrink, other.IdDrink);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_TEST_API_TestApi = new byte[0];
		static byte[] commonByteArray_TEST_API_TestApi = new byte[0];

		public int IdDrink;

		public int getIdDrink() {
			return this.IdDrink;
		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public String Category;

		public String getCategory() {
			return this.Category;
		}

		public String Alcoholic;

		public String getAlcoholic() {
			return this.Alcoholic;
		}

		public String Glass;

		public String getGlass() {
			return this.Glass;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Image;

		public String getImage() {
			return this.Image;
		}

		public List Ingredient1;

		public List getIngredient1() {
			return this.Ingredient1;
		}

		public List Ingredient2;

		public List getIngredient2() {
			return this.Ingredient2;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.Ingredient1 = (List) dis.readObject();

					this.Ingredient2 = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.IdDrink = dis.readInt();

					this.Name = readString(dis);

					this.Category = readString(dis);

					this.Alcoholic = readString(dis);

					this.Glass = readString(dis);

					this.Instructions = readString(dis);

					this.Image = readString(dis);

					this.Ingredient1 = (List) dis.readObject();

					this.Ingredient2 = (List) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.Ingredient1);

				// List

				dos.writeObject(this.Ingredient2);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.IdDrink);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.Category, dos);

				// String

				writeString(this.Alcoholic, dos);

				// String

				writeString(this.Glass, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Image, dos);

				// List

				dos.writeObject(this.Ingredient1);

				// List

				dos.writeObject(this.Ingredient2);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IdDrink=" + String.valueOf(IdDrink));
			sb.append(",Name=" + Name);
			sb.append(",Category=" + Category);
			sb.append(",Alcoholic=" + Alcoholic);
			sb.append(",Glass=" + Glass);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Image=" + Image);
			sb.append(",Ingredient1=" + String.valueOf(Ingredient1));
			sb.append(",Ingredient2=" + String.valueOf(Ingredient2));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_TEST_API_TestApi = new byte[0];
		static byte[] commonByteArray_TEST_API_TestApi = new byte[0];

		public Integer statusCode;

		public Integer getStatusCode() {
			return this.statusCode;
		}

		public routines.system.Document body;

		public routines.system.Document getBody() {
			return this.body;
		}

		public String string;

		public String getString() {
			return this.string;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.statusCode = readInteger(dis);

					this.body = (routines.system.Document) dis.readObject();

					this.string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.statusCode = readInteger(dis);

					this.body = (routines.system.Document) dis.readObject();

					this.string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.statusCode, dos);

				// Document

				dos.writeObject(this.body);

				// String

				writeString(this.string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.statusCode, dos);

				// Document

				dos.writeObject(this.body);

				// String

				writeString(this.string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("statusCode=" + String.valueOf(statusCode));
			sb.append(",body=" + String.valueOf(body));
			sb.append(",string=" + string);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_TEST_API_TestApi = new byte[0];
		static byte[] commonByteArray_TEST_API_TestApi = new byte[0];

		public String lettre;

		public String getLettre() {
			return this.lettre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TEST_API_TestApi.length) {
					if (length < 1024 && commonByteArray_TEST_API_TestApi.length == 0) {
						commonByteArray_TEST_API_TestApi = new byte[1024];
					} else {
						commonByteArray_TEST_API_TestApi = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TEST_API_TestApi, 0, length);
				strReturn = new String(commonByteArray_TEST_API_TestApi, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.lettre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TEST_API_TestApi) {

				try {

					int length = 0;

					this.lettre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lettre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lettre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lettre=" + lettre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row3Struct row3 = new row3Struct();
				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();
				testStruct test = new testStruct();
				testStruct row4 = test;
				row5Struct row5 = new row5Struct();
				row6Struct row6 = new row6Struct();

				/**
				 * [tFileOutputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileOutputExcel_1", false);
				start_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileOutputExcel_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tFileOutputExcel_1 = 0;

				int columnIndex_tFileOutputExcel_1 = 0;
				boolean headerIsInserted_tFileOutputExcel_1 = false;

				int nb_line_tFileOutputExcel_1 = 0;

				String fileName_tFileOutputExcel_1 = "C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/TEST_API/_output/Cocktail.xls";
				java.io.File file_tFileOutputExcel_1 = new java.io.File(fileName_tFileOutputExcel_1);
				boolean isFileGenerated_tFileOutputExcel_1 = true;
//create directory only if not exists
				java.io.File parentFile_tFileOutputExcel_1 = file_tFileOutputExcel_1.getParentFile();
				if (parentFile_tFileOutputExcel_1 != null && !parentFile_tFileOutputExcel_1.exists()) {

					parentFile_tFileOutputExcel_1.mkdirs();

				}

				jxl.write.WritableWorkbook writeableWorkbook_tFileOutputExcel_1 = null;
				jxl.write.WritableSheet writableSheet_tFileOutputExcel_1 = null;

				jxl.WorkbookSettings workbookSettings_tFileOutputExcel_1 = new jxl.WorkbookSettings();
				workbookSettings_tFileOutputExcel_1.setEncoding("ISO-8859-15");
				writeableWorkbook_tFileOutputExcel_1 = new jxl.write.biff.WritableWorkbookImpl(
						new java.io.BufferedOutputStream(new java.io.FileOutputStream(fileName_tFileOutputExcel_1)),
						true, workbookSettings_tFileOutputExcel_1);

				writableSheet_tFileOutputExcel_1 = writeableWorkbook_tFileOutputExcel_1.getSheet("Sheet1");
				if (writableSheet_tFileOutputExcel_1 == null) {
					writableSheet_tFileOutputExcel_1 = writeableWorkbook_tFileOutputExcel_1.createSheet("Sheet1",
							writeableWorkbook_tFileOutputExcel_1.getNumberOfSheets());
				}

				// modif start
				int startRowNum_tFileOutputExcel_1 = writableSheet_tFileOutputExcel_1.getRows();
				// modif end

				int[] fitWidth_tFileOutputExcel_1 = new int[8];
				for (int i_tFileOutputExcel_1 = 0; i_tFileOutputExcel_1 < 8; i_tFileOutputExcel_1++) {
					int fitCellViewSize_tFileOutputExcel_1 = writableSheet_tFileOutputExcel_1
							.getColumnView(i_tFileOutputExcel_1).getSize();
					fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1] = fitCellViewSize_tFileOutputExcel_1 / 256;
					if (fitCellViewSize_tFileOutputExcel_1 % 256 != 0) {
						fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1] += 1;
					}
				}

				if (startRowNum_tFileOutputExcel_1 == 0) {
					// modif end
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(0, nb_line_tFileOutputExcel_1, "IdDrink"));
					// modif end
					fitWidth_tFileOutputExcel_1[0] = fitWidth_tFileOutputExcel_1[0] > 7 ? fitWidth_tFileOutputExcel_1[0]
							: 7;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(1, nb_line_tFileOutputExcel_1, "Name"));
					// modif end
					fitWidth_tFileOutputExcel_1[1] = fitWidth_tFileOutputExcel_1[1] > 4 ? fitWidth_tFileOutputExcel_1[1]
							: 4;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(2, nb_line_tFileOutputExcel_1, "Category"));
					// modif end
					fitWidth_tFileOutputExcel_1[2] = fitWidth_tFileOutputExcel_1[2] > 8 ? fitWidth_tFileOutputExcel_1[2]
							: 8;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(3, nb_line_tFileOutputExcel_1, "Alcoholic"));
					// modif end
					fitWidth_tFileOutputExcel_1[3] = fitWidth_tFileOutputExcel_1[3] > 9 ? fitWidth_tFileOutputExcel_1[3]
							: 9;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(4, nb_line_tFileOutputExcel_1, "Glass"));
					// modif end
					fitWidth_tFileOutputExcel_1[4] = fitWidth_tFileOutputExcel_1[4] > 5 ? fitWidth_tFileOutputExcel_1[4]
							: 5;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(5, nb_line_tFileOutputExcel_1, "Instructions"));
					// modif end
					fitWidth_tFileOutputExcel_1[5] = fitWidth_tFileOutputExcel_1[5] > 12
							? fitWidth_tFileOutputExcel_1[5]
							: 12;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(6, nb_line_tFileOutputExcel_1, "Image"));
					// modif end
					fitWidth_tFileOutputExcel_1[6] = fitWidth_tFileOutputExcel_1[6] > 5 ? fitWidth_tFileOutputExcel_1[6]
							: 5;
					// modif start
					writableSheet_tFileOutputExcel_1
							.addCell(new jxl.write.Label(7, nb_line_tFileOutputExcel_1, "IngredientList"));
					// modif end
					fitWidth_tFileOutputExcel_1[7] = fitWidth_tFileOutputExcel_1[7] > 14
							? fitWidth_tFileOutputExcel_1[7]
							: 14;
					nb_line_tFileOutputExcel_1++;
					headerIsInserted_tFileOutputExcel_1 = true;
				}

				/**
				 * [tFileOutputExcel_1 begin ] stop
				 */

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tDBOutput_2 = 0;

				int nb_line_tDBOutput_2 = 0;
				int nb_line_update_tDBOutput_2 = 0;
				int nb_line_inserted_tDBOutput_2 = 0;
				int nb_line_deleted_tDBOutput_2 = 0;
				int nb_line_rejected_tDBOutput_2 = 0;

				int deletedCount_tDBOutput_2 = 0;
				int updatedCount_tDBOutput_2 = 0;
				int insertedCount_tDBOutput_2 = 0;
				int rowsToCommitCount_tDBOutput_2 = 0;

				String tableName_tDBOutput_2 = "Cocktail";
				boolean whetherReject_tDBOutput_2 = false;

				int batchSize_tDBOutput_2 = 10000;
				int batchSizeCounter_tDBOutput_2 = 0;
				int tmp_batchUpdateCount_tDBOutput_2 = 0;

				java.sql.Connection conn_tDBOutput_2 = null;

				java.lang.Class.forName("org.sqlite.JDBC");
				String url_tDBOutput_2 = "jdbc:sqlite:" + "/" + "C:/Users/totog/Cocktails.db";

				conn_tDBOutput_2 = java.sql.DriverManager.getConnection(url_tDBOutput_2);
				resourceMap.put("conn_tDBOutput_2", conn_tDBOutput_2);
				conn_tDBOutput_2.setAutoCommit(false);
				int commitEvery_tDBOutput_2 = 10000;
				int commitCounter_tDBOutput_2 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_2 = conn_tDBOutput_2.getMetaData();
				boolean whetherExist_tDBOutput_2 = false;
				try (java.sql.ResultSet rsTable_tDBOutput_2 = dbMetaData_tDBOutput_2.getTables(null, null, null,
						new String[] { "TABLE" })) {
					while (rsTable_tDBOutput_2.next()) {
						String table_tDBOutput_2 = rsTable_tDBOutput_2.getString("TABLE_NAME");
						if (table_tDBOutput_2.equalsIgnoreCase("Cocktail")) {
							whetherExist_tDBOutput_2 = true;
							break;
						}
					}
				}
				if (whetherExist_tDBOutput_2) {
					try (java.sql.Statement stmtDrop_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
						stmtDrop_tDBOutput_2.execute("DROP TABLE \"" + tableName_tDBOutput_2 + "\"");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
					stmtCreate_tDBOutput_2.execute("CREATE TABLE \"" + tableName_tDBOutput_2
							+ "\"(\"IdDrink\" INT  not null ,\"Name\" VARCHAR(255)  ,\"Category\" VARCHAR(255)  ,\"Alcoholic\" VARCHAR(255)  ,\"Glass\" VARCHAR(255)  ,\"Instructions\" VARCHAR(9999)  ,\"Image\" VARCHAR(9999)  ,\"null\" VARCHAR(0)   not null ,primary key(\"IdDrink\"))");
				}
				String insert_tDBOutput_2 = "INSERT INTO \"" + "Cocktail"
						+ "\" (\"IdDrink\",\"Name\",\"Category\",\"Alcoholic\",\"Glass\",\"Instructions\",\"Image\",\"null\") VALUES (?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tReplicate_1 begin ] start
				 */

				ok_Hash.put("tReplicate_1", false);
				start_Hash.put("tReplicate_1", System.currentTimeMillis());

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tReplicate_1 = 0;

				/**
				 * [tReplicate_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "test");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[8];

					public void addRow(String[] row) {

						for (int i = 0; i < 8; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 7 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 7 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[7] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "IdDrink", "Name", "Category", "Alcoholic", "Glass",
						"Instructions", "Image", "IngredientList", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					List IngredientList;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				testStruct test_tmp = new testStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tExtractJSONFields_1 begin ] start
				 */

				ok_Hash.put("tExtractJSONFields_1", false);
				start_Hash.put("tExtractJSONFields_1", System.currentTimeMillis());

				currentComponent = "tExtractJSONFields_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tExtractJSONFields_1 = 0;

				int nb_line_tExtractJSONFields_1 = 0;
				String jsonStr_tExtractJSONFields_1 = "";

				class JsonPathCache_tExtractJSONFields_1 {
					final java.util.Map<String, com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String, com.jayway.jsonpath.JsonPath>();

					public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
						if (jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
							return jsonPathString2compiledJsonPath.get(jsonPath);
						} else {
							com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath
									.compile(jsonPath);
							jsonPathString2compiledJsonPath.put(jsonPath, compiledLoopPath);
							return compiledLoopPath;
						}
					}
				}

				JsonPathCache_tExtractJSONFields_1 jsonPathCache_tExtractJSONFields_1 = new JsonPathCache_tExtractJSONFields_1();

				/**
				 * [tExtractJSONFields_1 begin ] stop
				 */

				/**
				 * [tRESTClient_2 begin ] start
				 */

				ok_Hash.put("tRESTClient_2", false);
				start_Hash.put("tRESTClient_2", System.currentTimeMillis());

				currentComponent = "tRESTClient_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tRESTClient_2 = 0;

				/**
				 * [tRESTClient_2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/TEST_API/_input/lettre.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/TEST_API/_input/lettre.csv",
								"UTF-8", ";", "\n", true, 1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row3 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row3 = new row3Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							columnIndexWithD_tFileInputDelimited_1 = 0;

							row3.lettre = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row3 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row3"
						if (row3 != null) {

							/**
							 * [tRESTClient_2 main ] start
							 */

							currentComponent = "tRESTClient_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row3"

								);
							}

							row1 = null;

// expected response body
							Object responseDoc_tRESTClient_2 = null;

							try {
								// request body
								org.dom4j.Document requestDoc_tRESTClient_2 = null;
								String requestString_tRESTClient_2 = null;

								Object requestBody_tRESTClient_2 = requestDoc_tRESTClient_2 != null
										? requestDoc_tRESTClient_2
										: requestString_tRESTClient_2;

								// resposne class name
								Class<?> responseClass_tRESTClient_2 = String.class;

								// create web client instance
								org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean factoryBean_tRESTClient_2 = new org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean();

								boolean inOSGi = routines.system.BundleUtils.inOSGi();

								final java.util.List<org.apache.cxf.feature.Feature> features_tRESTClient_2 = new java.util.ArrayList<org.apache.cxf.feature.Feature>();

								String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php";
								// {baseUri}tRESTClient
								factoryBean_tRESTClient_2
										.setServiceName(new javax.xml.namespace.QName(url, "tRESTClient"));
								factoryBean_tRESTClient_2.setAddress(url);

								factoryBean_tRESTClient_2.setFeatures(features_tRESTClient_2);

								java.util.List<Object> providers_tRESTClient_2 = new java.util.ArrayList<Object>();
								providers_tRESTClient_2.add(new org.apache.cxf.jaxrs.provider.dom4j.DOM4JProvider() {
									// workaround for https://jira.talendforge.org/browse/TESB-7276
									public org.dom4j.Document readFrom(Class<org.dom4j.Document> cls,
											java.lang.reflect.Type type, java.lang.annotation.Annotation[] anns,
											javax.ws.rs.core.MediaType mt,
											javax.ws.rs.core.MultivaluedMap<String, String> headers,
											java.io.InputStream is)
											throws IOException, javax.ws.rs.WebApplicationException {
										String contentLength = headers.getFirst("Content-Length");
										if (!org.apache.cxf.common.util.StringUtils.isEmpty(contentLength)
												&& Integer.valueOf(contentLength) <= 0) {
											try {
												return org.dom4j.DocumentHelper.parseText("<root/>");
											} catch (org.dom4j.DocumentException e_tRESTClient_2) {
												e_tRESTClient_2.printStackTrace();
											}
											return null;
										}
										return super.readFrom(cls, type, anns, mt, headers, is);
									}
								});
								org.apache.cxf.jaxrs.provider.json.JSONProvider jsonProvider_tRESTClient_2 = new org.apache.cxf.jaxrs.provider.json.JSONProvider();
								jsonProvider_tRESTClient_2.setIgnoreNamespaces(true);
								jsonProvider_tRESTClient_2.setAttributesToElements(true);

								jsonProvider_tRESTClient_2.setSupportUnwrapped(true);
								jsonProvider_tRESTClient_2.setWrapperName("root");

								jsonProvider_tRESTClient_2.setDropRootElement(false);
								jsonProvider_tRESTClient_2.setConvertTypesToStrings(false);
								providers_tRESTClient_2.add(jsonProvider_tRESTClient_2);
								factoryBean_tRESTClient_2.setProviders(providers_tRESTClient_2);
								factoryBean_tRESTClient_2.setTransportId("http://cxf.apache.org/transports/http");

								boolean use_auth_tRESTClient_2 = false;

								org.apache.cxf.jaxrs.client.WebClient webClient_tRESTClient_2 = factoryBean_tRESTClient_2
										.createWebClient();

								// set request path
								webClient_tRESTClient_2.path("");

								// set connection properties
								org.apache.cxf.jaxrs.client.ClientConfiguration clientConfig_tRESTClient_2 = org.apache.cxf.jaxrs.client.WebClient
										.getConfig(webClient_tRESTClient_2);
								org.apache.cxf.transport.http.auth.HttpAuthSupplier httpAuthSupplerHttpConduit = null;
								org.apache.cxf.transport.http.HTTPConduit conduit_tRESTClient_2 = clientConfig_tRESTClient_2
										.getHttpConduit();

								if (clientConfig_tRESTClient_2.getEndpoint() != null) {
									org.apache.cxf.service.model.EndpointInfo endpointInfo_tRESTClient_2 = clientConfig_tRESTClient_2
											.getEndpoint().getEndpointInfo();
									if (endpointInfo_tRESTClient_2 != null) {
										endpointInfo_tRESTClient_2.setProperty("enable.webclient.operation.reporting",
												true);
									}
								}

								if (!inOSGi) {
									conduit_tRESTClient_2.getClient().setReceiveTimeout((long) (60 * 1000L));
									conduit_tRESTClient_2.getClient().setConnectionTimeout((long) (30 * 1000L));
									boolean use_proxy_tRESTClient_2 = false;

								}

								// set Accept-Type
								webClient_tRESTClient_2.accept("application/json");

								// set optional query and header properties if any

								if (use_auth_tRESTClient_2 && "OAUTH2_BEARER".equals("BASIC")) {
									// set oAuth2 bearer token
									org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier authSupplier = new org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier();
									authSupplier.setAccessToken("");
									conduit_tRESTClient_2.setAuthSupplier(authSupplier);
								}

								// if FORM request then capture query parameters into Form, otherwise set them
								// as queries

								webClient_tRESTClient_2.query("f", row3.lettre);

								try {
									// start send request

									responseDoc_tRESTClient_2 = webClient_tRESTClient_2.get();
									javax.ws.rs.core.Response responseObjBase_tRESTClient_2 = (javax.ws.rs.core.Response) responseDoc_tRESTClient_2;
									int status_tRESTClient_2 = responseObjBase_tRESTClient_2.getStatus();
									if (status_tRESTClient_2 != 304 && status_tRESTClient_2 >= 300
											&& responseClass_tRESTClient_2 != javax.ws.rs.core.Response.class) {
										throw org.apache.cxf.jaxrs.utils.ExceptionUtils.toWebApplicationException(
												(javax.ws.rs.core.Response) responseObjBase_tRESTClient_2);
									}
									if (responseObjBase_tRESTClient_2.getEntity() != null) {
										responseDoc_tRESTClient_2 = responseObjBase_tRESTClient_2
												.readEntity(responseClass_tRESTClient_2);
									}

									int webClientResponseStatus_tRESTClient_2 = webClient_tRESTClient_2.getResponse()
											.getStatus();
									if (webClientResponseStatus_tRESTClient_2 >= 300) {
										throw new javax.ws.rs.WebApplicationException(
												webClient_tRESTClient_2.getResponse());
									}

									if (row1 == null) {
										row1 = new row1Struct();
									}

									row1.statusCode = webClientResponseStatus_tRESTClient_2;
									row1.string = "";

									{
										Object responseObj_tRESTClient_2 = responseDoc_tRESTClient_2;

										if (responseObj_tRESTClient_2 != null) {
											if (responseClass_tRESTClient_2 == String.class
													&& responseObj_tRESTClient_2 instanceof String) {
												row1.string = (String) responseObj_tRESTClient_2;
											} else {
												routines.system.Document responseTalendDoc_tRESTClient_2 = null;
												if (null != responseObj_tRESTClient_2) {
													responseTalendDoc_tRESTClient_2 = new routines.system.Document();
													if (responseObj_tRESTClient_2 instanceof org.dom4j.Document) {
														responseTalendDoc_tRESTClient_2.setDocument(
																(org.dom4j.Document) responseObj_tRESTClient_2);
													}
												}
												row1.body = responseTalendDoc_tRESTClient_2;
											}
										}
									}

									globalMap.put("tRESTClient_2_HEADERS",
											webClient_tRESTClient_2.getResponse().getHeaders());
									globalMap.put("tRESTClient_2_COOKIES",
											webClient_tRESTClient_2.getResponse().getCookies());

								} catch (javax.ws.rs.WebApplicationException ex_tRESTClient_2) {
									globalMap.put("tRESTClient_2_ERROR_MESSAGE", ex_tRESTClient_2.getMessage());

									throw ex_tRESTClient_2;

								}

							} catch (Exception e_tRESTClient_2) {
								globalMap.put("tRESTClient_2_ERROR_MESSAGE", e_tRESTClient_2.getMessage());

								throw new TalendException(e_tRESTClient_2, currentComponent, globalMap);

							}

							tos_count_tRESTClient_2++;

							/**
							 * [tRESTClient_2 main ] stop
							 */

							/**
							 * [tRESTClient_2 process_data_begin ] start
							 */

							currentComponent = "tRESTClient_2";

							/**
							 * [tRESTClient_2 process_data_begin ] stop
							 */
// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tExtractJSONFields_1 main ] start
								 */

								currentComponent = "tExtractJSONFields_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row1"

									);
								}

								if (row1.string != null) {// C_01
									jsonStr_tExtractJSONFields_1 = row1.string.toString();

									row2 = null;

									String loopPath_tExtractJSONFields_1 = "$.[*].[*]";
									java.util.List<Object> resultset_tExtractJSONFields_1 = new java.util.ArrayList<Object>();

									boolean isStructError_tExtractJSONFields_1 = true;
									com.jayway.jsonpath.ReadContext document_tExtractJSONFields_1 = null;
									try {
										document_tExtractJSONFields_1 = com.jayway.jsonpath.JsonPath
												.parse(jsonStr_tExtractJSONFields_1);
										com.jayway.jsonpath.JsonPath compiledLoopPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
												.getCompiledJsonPath(loopPath_tExtractJSONFields_1);
										Object result_tExtractJSONFields_1 = document_tExtractJSONFields_1.read(
												compiledLoopPath_tExtractJSONFields_1,
												net.minidev.json.JSONObject.class);
										if (result_tExtractJSONFields_1 instanceof net.minidev.json.JSONArray) {
											resultset_tExtractJSONFields_1 = (net.minidev.json.JSONArray) result_tExtractJSONFields_1;
										} else {
											resultset_tExtractJSONFields_1.add(result_tExtractJSONFields_1);
										}

										isStructError_tExtractJSONFields_1 = false;
									} catch (java.lang.Exception ex_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												ex_tExtractJSONFields_1.getMessage());
										System.err.println(ex_tExtractJSONFields_1.getMessage());
									}

									String jsonPath_tExtractJSONFields_1 = null;
									com.jayway.jsonpath.JsonPath compiledJsonPath_tExtractJSONFields_1 = null;

									Object value_tExtractJSONFields_1 = null;

									Object root_tExtractJSONFields_1 = null;
									for (int i_tExtractJSONFields_1 = 0; isStructError_tExtractJSONFields_1
											|| (i_tExtractJSONFields_1 < resultset_tExtractJSONFields_1
													.size()); i_tExtractJSONFields_1++) {
										if (!isStructError_tExtractJSONFields_1) {
											Object row_tExtractJSONFields_1 = resultset_tExtractJSONFields_1
													.get(i_tExtractJSONFields_1);
											row2 = null;
											row2 = new row2Struct();
											nb_line_tExtractJSONFields_1++;
											try {
												jsonPath_tExtractJSONFields_1 = "$.idDrink";
												compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
														.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

												try {

													value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
															.read(row_tExtractJSONFields_1);

													if (value_tExtractJSONFields_1 != null
															&& !value_tExtractJSONFields_1.toString().isEmpty()) {
														row2.IdDrink = ParserUtils
																.parseTo_int(value_tExtractJSONFields_1.toString());
													} else {
														row2.IdDrink =

																0

														;
													}
												} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
													globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
															e_tExtractJSONFields_1.getMessage());
													row2.IdDrink =

															0

													;
												}
												jsonPath_tExtractJSONFields_1 = "$.strDrink";
												compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
														.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

												try {

													value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
															.read(row_tExtractJSONFields_1);

													row2.Name = value_tExtractJSONFields_1 == null ?

															null

															: value_tExtractJSONFields_1.toString();
												} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
													globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
															e_tExtractJSONFields_1.getMessage());
													row2.Name =

															null

													;
												}
												jsonPath_tExtractJSONFields_1 = "$.strCategory";
												compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
														.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

												try {

													value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
															.read(row_tExtractJSONFields_1);

													row2.Category = value_tExtractJSONFields_1 == null ?

															null

															: value_tExtractJSONFields_1.toString();
												} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
													globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
															e_tExtractJSONFields_1.getMessage());
													row2.Category =

															null

													;
												}
												jsonPath_tExtractJSONFields_1 = "$.strAlcoholic";
												compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
														.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

												try {

													value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
															.read(row_tExtractJSONFields_1);

													row2.Alcoholic = value_tExtractJSONFields_1 == null ?

															null

															: value_tExtractJSONFields_1.toString();
												} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
													globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
															e_tExtractJSONFields_1.getMessage());
													row2.Alcoholic =

															null

													;
												}
												jsonPath_tExtractJSONFields_1 = "$.strGlass";
												compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
														.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

												try {

													value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
															.read(row_tExtractJSONFields_1);

													row2.Glass = value_tExtractJSONFields_1 == null ?

															null

															: value_tExtractJSONFields_1.toString();
												} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
													globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
															e_tExtractJSONFields_1.getMessage());
													row2.Glass =

															null

													;
												}
												jsonPath_tExtractJSONFields_1 = "$.strInstructions";
												compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
														.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

												try {

													value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
															.read(row_tExtractJSONFields_1);

													row2.Instructions = value_tExtractJSONFields_1 == null ?

															null

															: value_tExtractJSONFields_1.toString();
												} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
													globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
															e_tExtractJSONFields_1.getMessage());
													row2.Instructions =

															null

													;
												}
												jsonPath_tExtractJSONFields_1 = "$.strDrinkThumb";
												compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
														.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

												try {

													value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
															.read(row_tExtractJSONFields_1);

													row2.Image = value_tExtractJSONFields_1 == null ?

															null

															: value_tExtractJSONFields_1.toString();
												} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
													globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
															e_tExtractJSONFields_1.getMessage());
													row2.Image =

															null

													;
												}
												jsonPath_tExtractJSONFields_1 = "$.strIngredient1";
												compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
														.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

												try {

													value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
															.read(row_tExtractJSONFields_1);

													if (value_tExtractJSONFields_1 != null
															&& !value_tExtractJSONFields_1.toString().isEmpty()) {
														row2.Ingredient1 = ParserUtils.parseTo_List(
																value_tExtractJSONFields_1.toString(), ",");
													} else {
														row2.Ingredient1 =

																null

														;
													}
												} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
													globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
															e_tExtractJSONFields_1.getMessage());
													row2.Ingredient1 =

															null

													;
												}
												jsonPath_tExtractJSONFields_1 = "$.strIngredient2";
												compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
														.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

												try {

													value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
															.read(row_tExtractJSONFields_1);

													if (value_tExtractJSONFields_1 != null
															&& !value_tExtractJSONFields_1.toString().isEmpty()) {
														row2.Ingredient2 = ParserUtils.parseTo_List(
																value_tExtractJSONFields_1.toString(), ",");
													} else {
														row2.Ingredient2 =

																null

														;
													}
												} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
													globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
															e_tExtractJSONFields_1.getMessage());
													row2.Ingredient2 =

															null

													;
												}
											} catch (java.lang.Exception ex_tExtractJSONFields_1) {
												globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
														ex_tExtractJSONFields_1.getMessage());
												System.err.println(ex_tExtractJSONFields_1.getMessage());
												row2 = null;
											}

										}

										isStructError_tExtractJSONFields_1 = false;

//}

										tos_count_tExtractJSONFields_1++;

										/**
										 * [tExtractJSONFields_1 main ] stop
										 */

										/**
										 * [tExtractJSONFields_1 process_data_begin ] start
										 */

										currentComponent = "tExtractJSONFields_1";

										/**
										 * [tExtractJSONFields_1 process_data_begin ] stop
										 */
// Start of branch "row2"
										if (row2 != null) {

											/**
											 * [tMap_1 main ] start
											 */

											currentComponent = "tMap_1";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "row2"

												);
											}

											boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

											// ###############################
											// # Input tables (lookups)
											boolean rejectedInnerJoin_tMap_1 = false;
											boolean mainRowRejected_tMap_1 = false;

											// ###############################
											{ // start of Var scope

												// ###############################
												// # Vars tables

												Var__tMap_1__Struct Var = Var__tMap_1;
												Var.IngredientList = row2.Ingredient1 + "," + row2.Ingredient2;// ###############################
												// ###############################
												// # Output tables

												test = null;

// # Output table : 'test'
												test_tmp.IdDrink = 0;
												test_tmp.Name = null;
												test_tmp.Category = null;
												test_tmp.Alcoholic = null;
												test_tmp.Glass = null;
												test_tmp.Instructions = null;
												test_tmp.Image = null;
												test_tmp.IngredientList = Var.IngredientList;
												test = test_tmp;
// ###############################

											} // end of Var scope

											rejectedInnerJoin_tMap_1 = false;

											tos_count_tMap_1++;

											/**
											 * [tMap_1 main ] stop
											 */

											/**
											 * [tMap_1 process_data_begin ] start
											 */

											currentComponent = "tMap_1";

											/**
											 * [tMap_1 process_data_begin ] stop
											 */
// Start of branch "test"
											if (test != null) {

												/**
												 * [tLogRow_1 main ] start
												 */

												currentComponent = "tLogRow_1";

												if (execStat) {
													runStat.updateStatOnConnection(iterateId, 1, 1

															, "test"

													);
												}

///////////////////////		

												String[] row_tLogRow_1 = new String[8];

												row_tLogRow_1[0] = String.valueOf(test.IdDrink);

												if (test.Name != null) { //
													row_tLogRow_1[1] = String.valueOf(test.Name);

												} //

												if (test.Category != null) { //
													row_tLogRow_1[2] = String.valueOf(test.Category);

												} //

												if (test.Alcoholic != null) { //
													row_tLogRow_1[3] = String.valueOf(test.Alcoholic);

												} //

												if (test.Glass != null) { //
													row_tLogRow_1[4] = String.valueOf(test.Glass);

												} //

												if (test.Instructions != null) { //
													row_tLogRow_1[5] = String.valueOf(test.Instructions);

												} //

												if (test.Image != null) { //
													row_tLogRow_1[6] = String.valueOf(test.Image);

												} //

												if (test.IngredientList != null) { //
													row_tLogRow_1[7] = String.valueOf(test.IngredientList);

												} //

												util_tLogRow_1.addRow(row_tLogRow_1);
												nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

												row4 = test;

												tos_count_tLogRow_1++;

												/**
												 * [tLogRow_1 main ] stop
												 */

												/**
												 * [tLogRow_1 process_data_begin ] start
												 */

												currentComponent = "tLogRow_1";

												/**
												 * [tLogRow_1 process_data_begin ] stop
												 */

												/**
												 * [tReplicate_1 main ] start
												 */

												currentComponent = "tReplicate_1";

												if (execStat) {
													runStat.updateStatOnConnection(iterateId, 1, 1

															, "row4"

													);
												}

												row5 = new row5Struct();

												row5.IdDrink = row4.IdDrink;
												row5.Name = row4.Name;
												row5.Category = row4.Category;
												row5.Alcoholic = row4.Alcoholic;
												row5.Glass = row4.Glass;
												row5.Instructions = row4.Instructions;
												row5.Image = row4.Image;
												row5.IngredientList = row4.IngredientList;
												row6 = new row6Struct();

												row6.IdDrink = row4.IdDrink;
												row6.Name = row4.Name;
												row6.Category = row4.Category;
												row6.Alcoholic = row4.Alcoholic;
												row6.Glass = row4.Glass;
												row6.Instructions = row4.Instructions;
												row6.Image = row4.Image;
												row6.IngredientList = row4.IngredientList;

												tos_count_tReplicate_1++;

												/**
												 * [tReplicate_1 main ] stop
												 */

												/**
												 * [tReplicate_1 process_data_begin ] start
												 */

												currentComponent = "tReplicate_1";

												/**
												 * [tReplicate_1 process_data_begin ] stop
												 */

												/**
												 * [tFileOutputExcel_1 main ] start
												 */

												currentComponent = "tFileOutputExcel_1";

												if (execStat) {
													runStat.updateStatOnConnection(iterateId, 1, 1

															, "row5"

													);
												}

//modif start

												columnIndex_tFileOutputExcel_1 = 0;

												jxl.write.WritableCell cell_0_tFileOutputExcel_1 = new jxl.write.Number(
														columnIndex_tFileOutputExcel_1,
														startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
														row5.IdDrink);
//modif start					
												// If we keep the cell format from the existing cell in sheet

//modif ends							
												writableSheet_tFileOutputExcel_1.addCell(cell_0_tFileOutputExcel_1);
												int currentWith_0_tFileOutputExcel_1 = String.valueOf(
														((jxl.write.Number) cell_0_tFileOutputExcel_1).getValue())
														.trim().length();
												currentWith_0_tFileOutputExcel_1 = currentWith_0_tFileOutputExcel_1 > 10
														? 10
														: currentWith_0_tFileOutputExcel_1;
												fitWidth_tFileOutputExcel_1[0] = fitWidth_tFileOutputExcel_1[0] > currentWith_0_tFileOutputExcel_1
														? fitWidth_tFileOutputExcel_1[0]
														: currentWith_0_tFileOutputExcel_1 + 2;

												if (row5.Name != null) {

//modif start

													columnIndex_tFileOutputExcel_1 = 1;

													jxl.write.WritableCell cell_1_tFileOutputExcel_1 = new jxl.write.Label(
															columnIndex_tFileOutputExcel_1,
															startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
															row5.Name);
//modif start					
													// If we keep the cell format from the existing cell in sheet

//modif ends							
													writableSheet_tFileOutputExcel_1.addCell(cell_1_tFileOutputExcel_1);
													int currentWith_1_tFileOutputExcel_1 = cell_1_tFileOutputExcel_1
															.getContents().trim().length();
													fitWidth_tFileOutputExcel_1[1] = fitWidth_tFileOutputExcel_1[1] > currentWith_1_tFileOutputExcel_1
															? fitWidth_tFileOutputExcel_1[1]
															: currentWith_1_tFileOutputExcel_1 + 2;
												}

												if (row5.Category != null) {

//modif start

													columnIndex_tFileOutputExcel_1 = 2;

													jxl.write.WritableCell cell_2_tFileOutputExcel_1 = new jxl.write.Label(
															columnIndex_tFileOutputExcel_1,
															startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
															row5.Category);
//modif start					
													// If we keep the cell format from the existing cell in sheet

//modif ends							
													writableSheet_tFileOutputExcel_1.addCell(cell_2_tFileOutputExcel_1);
													int currentWith_2_tFileOutputExcel_1 = cell_2_tFileOutputExcel_1
															.getContents().trim().length();
													fitWidth_tFileOutputExcel_1[2] = fitWidth_tFileOutputExcel_1[2] > currentWith_2_tFileOutputExcel_1
															? fitWidth_tFileOutputExcel_1[2]
															: currentWith_2_tFileOutputExcel_1 + 2;
												}

												if (row5.Alcoholic != null) {

//modif start

													columnIndex_tFileOutputExcel_1 = 3;

													jxl.write.WritableCell cell_3_tFileOutputExcel_1 = new jxl.write.Label(
															columnIndex_tFileOutputExcel_1,
															startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
															row5.Alcoholic);
//modif start					
													// If we keep the cell format from the existing cell in sheet

//modif ends							
													writableSheet_tFileOutputExcel_1.addCell(cell_3_tFileOutputExcel_1);
													int currentWith_3_tFileOutputExcel_1 = cell_3_tFileOutputExcel_1
															.getContents().trim().length();
													fitWidth_tFileOutputExcel_1[3] = fitWidth_tFileOutputExcel_1[3] > currentWith_3_tFileOutputExcel_1
															? fitWidth_tFileOutputExcel_1[3]
															: currentWith_3_tFileOutputExcel_1 + 2;
												}

												if (row5.Glass != null) {

//modif start

													columnIndex_tFileOutputExcel_1 = 4;

													jxl.write.WritableCell cell_4_tFileOutputExcel_1 = new jxl.write.Label(
															columnIndex_tFileOutputExcel_1,
															startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
															row5.Glass);
//modif start					
													// If we keep the cell format from the existing cell in sheet

//modif ends							
													writableSheet_tFileOutputExcel_1.addCell(cell_4_tFileOutputExcel_1);
													int currentWith_4_tFileOutputExcel_1 = cell_4_tFileOutputExcel_1
															.getContents().trim().length();
													fitWidth_tFileOutputExcel_1[4] = fitWidth_tFileOutputExcel_1[4] > currentWith_4_tFileOutputExcel_1
															? fitWidth_tFileOutputExcel_1[4]
															: currentWith_4_tFileOutputExcel_1 + 2;
												}

												if (row5.Instructions != null) {

//modif start

													columnIndex_tFileOutputExcel_1 = 5;

													jxl.write.WritableCell cell_5_tFileOutputExcel_1 = new jxl.write.Label(
															columnIndex_tFileOutputExcel_1,
															startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
															row5.Instructions);
//modif start					
													// If we keep the cell format from the existing cell in sheet

//modif ends							
													writableSheet_tFileOutputExcel_1.addCell(cell_5_tFileOutputExcel_1);
													int currentWith_5_tFileOutputExcel_1 = cell_5_tFileOutputExcel_1
															.getContents().trim().length();
													fitWidth_tFileOutputExcel_1[5] = fitWidth_tFileOutputExcel_1[5] > currentWith_5_tFileOutputExcel_1
															? fitWidth_tFileOutputExcel_1[5]
															: currentWith_5_tFileOutputExcel_1 + 2;
												}

												if (row5.Image != null) {

//modif start

													columnIndex_tFileOutputExcel_1 = 6;

													jxl.write.WritableCell cell_6_tFileOutputExcel_1 = new jxl.write.Label(
															columnIndex_tFileOutputExcel_1,
															startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
															row5.Image);
//modif start					
													// If we keep the cell format from the existing cell in sheet

//modif ends							
													writableSheet_tFileOutputExcel_1.addCell(cell_6_tFileOutputExcel_1);
													int currentWith_6_tFileOutputExcel_1 = cell_6_tFileOutputExcel_1
															.getContents().trim().length();
													fitWidth_tFileOutputExcel_1[6] = fitWidth_tFileOutputExcel_1[6] > currentWith_6_tFileOutputExcel_1
															? fitWidth_tFileOutputExcel_1[6]
															: currentWith_6_tFileOutputExcel_1 + 2;
												}

												if (row5.IngredientList != null) {

//modif start

													columnIndex_tFileOutputExcel_1 = 7;

													jxl.write.WritableCell cell_7_tFileOutputExcel_1 = new jxl.write.Label(
															columnIndex_tFileOutputExcel_1,
															startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end					
															row5.IngredientList.toString());
//modif start					
													// If we keep the cell format from the existing cell in sheet

//modif ends							
													writableSheet_tFileOutputExcel_1.addCell(cell_7_tFileOutputExcel_1);
													int currentWith_7_tFileOutputExcel_1 = cell_7_tFileOutputExcel_1
															.getContents().trim().length();
													fitWidth_tFileOutputExcel_1[7] = fitWidth_tFileOutputExcel_1[7] > currentWith_7_tFileOutputExcel_1
															? fitWidth_tFileOutputExcel_1[7]
															: currentWith_7_tFileOutputExcel_1 + 2;
												}

												nb_line_tFileOutputExcel_1++;

												tos_count_tFileOutputExcel_1++;

												/**
												 * [tFileOutputExcel_1 main ] stop
												 */

												/**
												 * [tFileOutputExcel_1 process_data_begin ] start
												 */

												currentComponent = "tFileOutputExcel_1";

												/**
												 * [tFileOutputExcel_1 process_data_begin ] stop
												 */

												/**
												 * [tFileOutputExcel_1 process_data_end ] start
												 */

												currentComponent = "tFileOutputExcel_1";

												/**
												 * [tFileOutputExcel_1 process_data_end ] stop
												 */

												/**
												 * [tDBOutput_2 main ] start
												 */

												currentComponent = "tDBOutput_2";

												if (execStat) {
													runStat.updateStatOnConnection(iterateId, 1, 1

															, "row6"

													);
												}

												whetherReject_tDBOutput_2 = false;
												pstmt_tDBOutput_2.setInt(1, row6.IdDrink);

												if (row6.Name == null) {
													pstmt_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
												} else {
													pstmt_tDBOutput_2.setString(2, row6.Name);
												}

												if (row6.Category == null) {
													pstmt_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
												} else {
													pstmt_tDBOutput_2.setString(3, row6.Category);
												}

												if (row6.Alcoholic == null) {
													pstmt_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
												} else {
													pstmt_tDBOutput_2.setString(4, row6.Alcoholic);
												}

												if (row6.Glass == null) {
													pstmt_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
												} else {
													pstmt_tDBOutput_2.setString(5, row6.Glass);
												}

												if (row6.Instructions == null) {
													pstmt_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
												} else {
													pstmt_tDBOutput_2.setString(6, row6.Instructions);
												}

												if (row6.Image == null) {
													pstmt_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
												} else {
													pstmt_tDBOutput_2.setString(7, row6.Image);
												}

												pstmt_tDBOutput_2.setObject(8, row6.IngredientList);

												pstmt_tDBOutput_2.addBatch();
												batchSizeCounter_tDBOutput_2++;
												nb_line_tDBOutput_2++;
												if ((batchSize_tDBOutput_2 > 0)
														&& (batchSize_tDBOutput_2 <= batchSizeCounter_tDBOutput_2)) {
													int[] status_tDBOutput_2 = null;
													int countSum_tDBOutput_2 = 0;
													try {
														batchSizeCounter_tDBOutput_2 = 0;
														status_tDBOutput_2 = pstmt_tDBOutput_2.executeBatch();
														for (int countEach_tDBOutput_2 : status_tDBOutput_2) {
															countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0
																	: countEach_tDBOutput_2);
														}
													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
														for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
															countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0
																	: countEach_tDBOutput_2);
														}
														System.err.println(e.getMessage());
													}
													try {
														tmp_batchUpdateCount_tDBOutput_2 = pstmt_tDBOutput_2
																.getUpdateCount();
													} catch (java.sql.SQLException e) {
														globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
														System.err.println(e.getMessage());
													}
													tmp_batchUpdateCount_tDBOutput_2 = tmp_batchUpdateCount_tDBOutput_2 > countSum_tDBOutput_2
															? tmp_batchUpdateCount_tDBOutput_2
															: countSum_tDBOutput_2;
													rowsToCommitCount_tDBOutput_2 += tmp_batchUpdateCount_tDBOutput_2;
													insertedCount_tDBOutput_2 += tmp_batchUpdateCount_tDBOutput_2;
												}
												commitCounter_tDBOutput_2++;
												if (commitEvery_tDBOutput_2 <= commitCounter_tDBOutput_2) {
													try {
														if (batchSizeCounter_tDBOutput_2 > 0) {
															int countSum_tDBOutput_2 = 0;

															for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2
																	.executeBatch()) {
																countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0
																		: countEach_tDBOutput_2);
															}
															rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

															insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

															batchSizeCounter_tDBOutput_2 = 0;
														}
													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
														int countSum_tDBOutput_2 = 0;
														for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
															countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0
																	: countEach_tDBOutput_2);
														}
														rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;
														insertedCount_tDBOutput_2 += countSum_tDBOutput_2;
														System.err.println(e.getMessage());
													}
													if (rowsToCommitCount_tDBOutput_2 != 0) {

													}
													conn_tDBOutput_2.commit();
													if (rowsToCommitCount_tDBOutput_2 != 0) {

														rowsToCommitCount_tDBOutput_2 = 0;
													}
													commitCounter_tDBOutput_2 = 0;
												}

												tos_count_tDBOutput_2++;

												/**
												 * [tDBOutput_2 main ] stop
												 */

												/**
												 * [tDBOutput_2 process_data_begin ] start
												 */

												currentComponent = "tDBOutput_2";

												/**
												 * [tDBOutput_2 process_data_begin ] stop
												 */

												/**
												 * [tDBOutput_2 process_data_end ] start
												 */

												currentComponent = "tDBOutput_2";

												/**
												 * [tDBOutput_2 process_data_end ] stop
												 */

												/**
												 * [tReplicate_1 process_data_end ] start
												 */

												currentComponent = "tReplicate_1";

												/**
												 * [tReplicate_1 process_data_end ] stop
												 */

												/**
												 * [tLogRow_1 process_data_end ] start
												 */

												currentComponent = "tLogRow_1";

												/**
												 * [tLogRow_1 process_data_end ] stop
												 */

											} // End of branch "test"

											/**
											 * [tMap_1 process_data_end ] start
											 */

											currentComponent = "tMap_1";

											/**
											 * [tMap_1 process_data_end ] stop
											 */

										} // End of branch "row2"

										// end for
									}

								} // C_01

								/**
								 * [tExtractJSONFields_1 process_data_end ] start
								 */

								currentComponent = "tExtractJSONFields_1";

								/**
								 * [tExtractJSONFields_1 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tRESTClient_2 process_data_end ] start
							 */

							currentComponent = "tRESTClient_2";

							/**
							 * [tRESTClient_2 process_data_end ] stop
							 */

						} // End of branch "row3"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/TEST_API/_input/lettre.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tRESTClient_2 end ] start
				 */

				currentComponent = "tRESTClient_2";

				if (globalMap.get("tRESTClient_2_NB_LINE") == null) {
					globalMap.put("tRESTClient_2_NB_LINE", 1);
				}

// [tRESTCliend_end]
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tRESTClient_2", true);
				end_Hash.put("tRESTClient_2", System.currentTimeMillis());

				/**
				 * [tRESTClient_2 end ] stop
				 */

				/**
				 * [tExtractJSONFields_1 end ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				globalMap.put("tExtractJSONFields_1_NB_LINE", nb_line_tExtractJSONFields_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tExtractJSONFields_1", true);
				end_Hash.put("tExtractJSONFields_1", System.currentTimeMillis());

				/**
				 * [tExtractJSONFields_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "test");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tReplicate_1", true);
				end_Hash.put("tReplicate_1", System.currentTimeMillis());

				/**
				 * [tReplicate_1 end ] stop
				 */

				/**
				 * [tFileOutputExcel_1 end ] start
				 */

				currentComponent = "tFileOutputExcel_1";

				writeableWorkbook_tFileOutputExcel_1.write();
				writeableWorkbook_tFileOutputExcel_1.close();
				if (headerIsInserted_tFileOutputExcel_1 && nb_line_tFileOutputExcel_1 > 0) {
					nb_line_tFileOutputExcel_1 = nb_line_tFileOutputExcel_1 - 1;
				}
				globalMap.put("tFileOutputExcel_1_NB_LINE", nb_line_tFileOutputExcel_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tFileOutputExcel_1", true);
				end_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileOutputExcel_1 end ] stop
				 */

				/**
				 * [tDBOutput_2 end ] start
				 */

				currentComponent = "tDBOutput_2";

				int[] status_tDBOutput_2 = null;
				int countSum_tDBOutput_2 = 0;
				try {
					if (pstmt_tDBOutput_2 != null && batchSizeCounter_tDBOutput_2 > 0) {
						status_tDBOutput_2 = pstmt_tDBOutput_2.executeBatch();
						for (int countEach_tDBOutput_2 : status_tDBOutput_2) {
							countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
						}
					}
				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
					for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
						countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
					}
					System.err.println(e.getMessage());
				}
				if (pstmt_tDBOutput_2 != null && batchSizeCounter_tDBOutput_2 > 0) {
					try {
						tmp_batchUpdateCount_tDBOutput_2 = pstmt_tDBOutput_2.getUpdateCount();
					} catch (java.sql.SQLException e) {
						globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

					}
					tmp_batchUpdateCount_tDBOutput_2 = tmp_batchUpdateCount_tDBOutput_2 > countSum_tDBOutput_2
							? tmp_batchUpdateCount_tDBOutput_2
							: countSum_tDBOutput_2;
					rowsToCommitCount_tDBOutput_2 += tmp_batchUpdateCount_tDBOutput_2;
					insertedCount_tDBOutput_2 += tmp_batchUpdateCount_tDBOutput_2;
				}
				if (pstmt_tDBOutput_2 != null) {
					pstmt_tDBOutput_2.close();
					resourceMap.remove("pstmt_tDBOutput_2");
				}
				resourceMap.put("statementClosed_tDBOutput_2", true);
				if (commitCounter_tDBOutput_2 > 0 && rowsToCommitCount_tDBOutput_2 != 0) {
				}
				conn_tDBOutput_2.commit();
				if (commitCounter_tDBOutput_2 > 0 && rowsToCommitCount_tDBOutput_2 != 0) {
					rowsToCommitCount_tDBOutput_2 = 0;
				}
				commitCounter_tDBOutput_2 = 0;
				conn_tDBOutput_2.close();
				resourceMap.put("finish_tDBOutput_2", true);

				int rejectedCount_tDBOutput_2 = 0;
				nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
				nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
				nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
				nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

				globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tRESTClient_2 finally ] start
				 */

				currentComponent = "tRESTClient_2";

				/**
				 * [tRESTClient_2 finally ] stop
				 */

				/**
				 * [tExtractJSONFields_1 finally ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				/**
				 * [tExtractJSONFields_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tReplicate_1 finally ] start
				 */

				currentComponent = "tReplicate_1";

				/**
				 * [tReplicate_1 finally ] stop
				 */

				/**
				 * [tFileOutputExcel_1 finally ] start
				 */

				currentComponent = "tFileOutputExcel_1";

				/**
				 * [tFileOutputExcel_1 finally ] stop
				 */

				/**
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
						if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_2")) != null) {
							pstmtToClose_tDBOutput_2.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_2") == null) {
						java.sql.Connection ctn_tDBOutput_2 = null;
						if ((ctn_tDBOutput_2 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_2")) != null) {
							try {
								ctn_tDBOutput_2.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_2) {
								String errorMessage_tDBOutput_2 = "failed to close the connection in tDBOutput_2 :"
										+ sqlEx_tDBOutput_2.getMessage();
								System.err.println(errorMessage_tDBOutput_2);
							}
						}
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final TestApi TestApiClass = new TestApi();

		int exitCode = TestApiClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = TestApi.class.getClassLoader()
					.getResourceAsStream("test_api/testapi_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = TestApi.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : TestApi");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 152607 characters generated by Talend Open Studio for Data Integration on the
 * 26 janvier 2024, 14:54:21 CET
 ************************************************************************************************/