package com.effectivejava.note1;

public class ComputeProvider implements Provider
{

	@Override
	public Service newService()
	{
		// TODO Auto-generated method stub
		return new CloudComputeService();
	}

}
