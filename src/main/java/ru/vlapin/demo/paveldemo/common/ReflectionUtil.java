package ru.vlapin.demo.paveldemo.common;

import static io.vavr.API.unchecked;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;

import io.vavr.CheckedFunction1;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface ReflectionUtil {

  Function<String, Class<?>> CLASS_FOR_NAME = CheckedFunction1.<String, Class<?>>of(Class::forName).unchecked();

  /**
   * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
   *
   * @param packageName The base package
   * @return The classes
   */
  static Stream<Class<?>> getClasses(String packageName) {

    return unchecked(ClassLoader::getResources)
               .andThen(Enumeration::asIterator)
               .andThen(urlIterator -> spliteratorUnknownSize(urlIterator, ORDERED))
               .andThen(urlSpliterator -> StreamSupport.stream(urlSpliterator, false))
               .reversed()
               .apply(packageName.replace('.', '/'))
               .compose(Thread::getContextClassLoader)
               .apply(Thread.currentThread())
               .map(URL::getFile)
               .map(File::new)
               .flatMap(dir -> findClasses(dir, packageName));
  }

  /**
   * Recursive method used to find all classes in a given directory and subdirectories.
   *
   * @param directory   The base directory
   * @param packageName The package name for classes found inside the base directory
   * @return The classes
   */
  private static Stream<Class<?>> findClasses(File directory, String packageName) {
    return Stream.of(directory)
               .filter(File::exists)
               .map(File::listFiles)
               .flatMap(Arrays::stream)
               .flatMap(file -> file.isDirectory()
                                    ? findClasses(file, packageName + "." + file.getName())
                                    : findClass(file.getName(), packageName)
               );
  }

  private static Stream<? extends Class<?>> findClass(String fileName, String packageName) {
    return Stream.of(fileName)
               .filter(name -> name.endsWith(".class"))
               .map(name -> CLASS_FOR_NAME.apply(packageName + '.' + name.substring(0, name.length() - 6)));
  }
}
