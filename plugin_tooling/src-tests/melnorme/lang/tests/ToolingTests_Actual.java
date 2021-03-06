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
package melnorme.lang.tests;

import com.github.rustdt.tooling.cargo.CargoManifest;

import melnorme.lang.tooling.LANG_SPECIFIC;
import melnorme.lang.tooling.bundle.BundleInfo;
import melnorme.lang.tooling.bundle.FileRef;
import melnorme.utilbox.collections.ArrayList2;
import melnorme.utilbox.misc.Location;

@LANG_SPECIFIC
public class ToolingTests_Actual {
	
	public static Location SAMPLE_SDK_PATH = LangToolingTestResources.getTestResourceLoc("mock_sdk");
	
	public static BundleInfo createSampleBundleInfoA(String name, String version) {
		return new BundleInfo(new CargoManifest(name, version, null, 
			null, 
			new ArrayList2<>(
				new FileRef("sampleConfig", null)
			)
			, null
		));
	}
	
}