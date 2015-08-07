package com.skynet.vmall.base.setup;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Modules(packages =
{ "com.blue.wxmp", "com.skynet.framework", "com.skynet.app", "com.skynet.vmall"}, scanPackage = true)
@IocBy(type = ComboIocProvider.class, args =
{ "*org.nutz.ioc.loader.json.JsonLoader", "ioc.js", "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.blue.wxmp", "com.skynet.framework", "com.skynet.app", "com.skynet.vmall"})
@SetupBy(AppSetup.class)
public class MainModule
{

}
