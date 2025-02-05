package org.bukkit.plugin.lib;

import org.bukkit.plugin.PluginDescriptionFile;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class LibraryLoader
{
    public ClassLoader createLoader(PluginDescriptionFile description) throws MalformedURLException
    {
        if (description.getDependencies().isEmpty()) return null;

        List<URL> urls = new ArrayList<>();
        for (String dependency : description.getDependencies())
        {
            MavenLibrary library = new MavenLibrary(dependency);
            library.download();
            urls.add(library.getFile().toURI().toURL());
        }

        System.out.println("Loaded " + urls.size() + " libraries for plugin: " + description.getName());

        URLClassLoader loader = new URLClassLoader(urls.toArray(new URL[0]), getClass().getClassLoader());

        return loader;
    }
}
