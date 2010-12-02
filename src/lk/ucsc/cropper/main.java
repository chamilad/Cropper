package lk.ucsc.cropper;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class main extends Activity {

	EditText crop_code;
	TextView answer_display;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		crop_code = (EditText) findViewById(R.id.crop_code);
		answer_display = (TextView) findViewById(R.id.answer);

	}

	public void onButtonClick(View v) {
		String NAMESPACE = "http://schemas.icta.lk/xsd/crop/handler/v1/";
		//String NAMESPACE = "http://wtp";
		String URL = "http://www.srilanka.lk:9080/services/CropServiceProxy.CropServiceProxyHttpSoap12Endpoint";
		//String URL = "http://www.srilanka.lk:9080/services/TemperatureConverterProxy.TemperatureConverterProxyHttpSoap11Endpoint";
		//String URL = "https://www.srilanka.lk:9443/services/CropServiceProxy.CropServiceProxyHttpsSoap12Endpoint";
		//String URL = "http://www.srilanka.lk:9080/services/CropServiceProxy.CropServiceProxyHttpSoap11Endpoint";
		String SOAP_ACTION = "http://schemas.icta.lk/xsd/crop/handler/v1/getCropDataList";

		String method_name = "getCropDataList";
		//String method_name = "celsiusToFarenheit";

		SoapObject request = new SoapObject(NAMESPACE, method_name);
		request.addProperty("code", String.valueOf(crop_code.getText().toString()));
		//request.addProperty("celsius", String.valueOf(crop_code.getText()	.toString()));

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);

		AndroidHttpTransport http_transport = new AndroidHttpTransport(URL);
		try {
			http_transport.call(SOAP_ACTION, envelope);

			SoapObject result = (SoapObject) envelope.bodyIn;
			//Object result = (Object) envelope.bodyIn;
			//SoapObject result = (SoapObject) envelope.getResponse();
			//System.out.println(String.valueOf(result.getProperty("return")));
			System.out.println(String.valueOf(result.getPropertyCount()));
			//System.out.println(result.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// String result_string[] = (String[])result;

		answer_display.setText("returned");

	}
}