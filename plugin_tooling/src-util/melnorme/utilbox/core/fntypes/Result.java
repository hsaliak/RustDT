/*******************************************************************************
 * Copyright (c) 2016 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.utilbox.core.fntypes;

import static melnorme.utilbox.core.Assert.AssertNamespace.assertTrue;

public class Result<DATA, EXC extends Throwable> {
	
	protected final DATA resultValue;
	/** Note: resultException is either a EXC, or a RuntimeException. */
	protected final Throwable resultException;
	
	public static <DATA, EXC extends Throwable> Result<DATA, EXC> fromValue(DATA resultValue) {
		return new Result<>(resultValue);
	}
	
	public static <DATA, EXC extends Throwable> Result<DATA, EXC> fromException(EXC exception) {
		return new Result<>(null, exception);
	}
	
	public static <DATA, EXC extends Throwable> Result<DATA, EXC> fromRuntimeException(RuntimeException exception) {
		return new Result<>(null, exception, null);
	}
	
	public Result(DATA resultValue) {
		this(resultValue, null);
	}
	
	public Result(DATA resultValue, EXC resultException) {
		this(resultValue, resultException, null);
	}
	
	protected Result(DATA resultValue, Throwable resultException, @SuppressWarnings("unused") Void dummy) {
		this.resultValue = resultValue;
		this.resultException = resultException;
		assertTrue(resultValue == null || resultException == null);
	}
	
	public DATA get() throws EXC {
		throwIfExceptionResult();
		return resultValue;
	}
	
	public boolean isSuccessful() {
		return resultException == null;
	}
	
	public boolean isException() {
		return !isSuccessful();
	}
	
	public DATA getOrNull() {
		try {
			return get();
		} catch(RuntimeException e) {
			throw e;
		} catch(Throwable e) {
			return null;
		}
	}
	
	public Throwable getResultException() {
		return resultException;
	}
	
	@SuppressWarnings("unchecked")
	protected void throwIfExceptionResult() throws EXC  {
		if(resultException instanceof RuntimeException) {
			throw (RuntimeException) resultException;
		}
		if(resultException != null) {
			throw (EXC) resultException;
		}
	}
	
}