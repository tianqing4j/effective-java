package org.liubey.example.effectivejava;

/**
 * 
 * Builder Pattern
 * 
 * call method
 * 
 * NeedManyParameter var = new NeedManyParameter.Builder("1","2")
 * .optional1("3").optional2("4").build();
 * 
 * @author liubey.org
 *
 */
public class NeedManyParameter {
	//required parameters
	private final String requiredParam1;
	private final String requiredParam2;

	//optional parameters
	private final String optionalParam1;
	private final String optionalParam2;
	
	public static class Builder {
		//required parameters
		private final String requiredParam1;
		private final String requiredParam2;

		//optional parameters  default value
		private String optionalParam1 = "";
		private String optionalParam2 = "";
		
		public Builder(String requiredParam1, String requiredParam2) {
			this.requiredParam1 = requiredParam1;
			this.requiredParam2 = requiredParam2;
		}
		
		public Builder optional1(String optionalParam1) {
			this.optionalParam1 = optionalParam1;
			return this;
		}
		
		public Builder optional2(String optionalParam2) {
			this.optionalParam2 = optionalParam2;
			return this;
		}
		
		public NeedManyParameter build() {
			return new NeedManyParameter(this);
		}
	}
	
	private NeedManyParameter(Builder builder) {
		requiredParam1 = builder.requiredParam1;
		requiredParam2 = builder.requiredParam2;
		
		optionalParam1 = builder.optionalParam1;
		optionalParam2 = builder.optionalParam2;
	}
	
}
