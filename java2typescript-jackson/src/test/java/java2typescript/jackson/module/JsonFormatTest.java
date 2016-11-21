package java2typescript.jackson.module;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import java2typescript.jackson.module.grammar.Module;
import java2typescript.jackson.module.util.ExpectedOutputChecker;
import java2typescript.jackson.module.util.TestUtil;
import java2typescript.jackson.module.writer.ExternalModuleFormatWriter;
import org.junit.Test;

public class JsonFormatTest {

	class TestClassWithJsonFormatFields {
		public XMLGregorianCalendar _XMLGregorianCalendar;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'", timezone = "GMT")
		public XMLGregorianCalendar _XMLGregorianCalendarAsString;
	}

	@Test
	public void classFieldsAnnotatedWithJsonFormat() throws IOException {
		// Arrange
		Module module = TestUtil.createTestModule(null, TestClassWithJsonFormatFields.class);
		Writer out = new StringWriter();

		// Act
		new ExternalModuleFormatWriter().write(module, out);
		out.close();
		System.out.println(out);

		// Assert
		ExpectedOutputChecker.checkOutputFromFile(out);
	}
}
