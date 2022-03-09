package parser;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class ParserJsonFormat {

    public static String getJson(Object obj) throws IllegalAccessException {
        StringBuilder stringJson = new StringBuilder();
        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        stringJson.append("{");
        for (int i = 0; i < declaredFields.length - 1; i++) {

            Field f = declaredFields[i];
            if (isNumber(f, obj)) {
                if(i != 0)
                    stringJson.append(",");
                stringJson.append(getFormatJsonJustField(f, obj));
            } else if (isCollection(f)) {
                if(i != 0)
                    stringJson.append(",");
                stringJson.append(getFormatJsonJustListField(f, obj));
            }
        }
        stringJson.append("}");
        return String.valueOf(stringJson);
    }


    private static StringBuilder getFormatJsonJustField(Field f, Object obj) throws IllegalAccessException {
        f.setAccessible(true);
        StringBuilder stringJson = new StringBuilder();
        if (isNumber(f, obj)) {
            getLineNumberJson(stringJson, f, obj);
        } else if (f.getType() == String.class) {
            getLineStringJson(stringJson, f, obj);
        }
        f.setAccessible(false);
        return stringJson;
    }

    private static StringBuilder getLineNumberJson(StringBuilder stringJson, Field f, Object obj) throws IllegalAccessException {
        return stringJson.append("\"" + f.getName() + "\":" + f.get(obj));
    }

    private static StringBuilder getLineStringJson(StringBuilder stringJson, Field f, Object obj) throws IllegalAccessException {
        return stringJson.append("\"" + f.getName() + "\":" + "\"" + f.get(obj) + "\"");
    }

    private static boolean isNumber(Field f, Object obj) {
        boolean result = f.getType().isPrimitive() ||
                f.getType().isAssignableFrom(Integer.class) ||
                f.getType().isAssignableFrom(Short.class) ||
                f.getType().isAssignableFrom(Long.class) ||
                f.getType().isAssignableFrom(Byte.class) ||
                f.getType().isAssignableFrom(Float.class) ||
                f.getType().isAssignableFrom(Double.class) ||
                f.getType().isAssignableFrom(Character.class) ||
                f.getType().isAssignableFrom(Boolean.class);

        return result;
    }


    private static StringBuilder getFormatJsonJustListField(Field f, Object obj) throws IllegalAccessException {
        f.setAccessible(true);
        Type genericType = f.getGenericType();
        StringBuilder stringJson = new StringBuilder();
        String typeName = genericType.getTypeName();

        if (isCollection(f)) {
            if (typeName.equals("java.util.List<java.lang.String>")) {
                List<String> list = (List<String>) f.get(obj);
                stringJson.append("\"" + f.getName() + "\":[");
                for (int i = 0; i < list.size(); i++) {
                    stringJson.append("\"" + list.get(i) + "\"");
                    if (i < list.size() - 1)
                        stringJson.append(",");
                }
                stringJson.append("]");

            } else if (typeName.equals("java.util.List<java.lang.Integer>") ||
                    typeName.equals("java.util.List<java.lang.Short>") ||
                    typeName.equals("java.util.List<java.lang.Long>") ||
                    typeName.equals("java.util.List<java.lang.Byte>") ||
                    typeName.equals("java.util.List<java.lang.Float>") ||
                    typeName.equals("java.util.List<java.lang.Double>") ||
                    typeName.equals("java.util.List<java.lang.Character>") ||
                    typeName.equals("java.util.List<java.lang.Boolean>")) {
                List<Integer> list = (List<Integer>) f.get(obj);
                stringJson.append("\"" + f.getName() + "\":[");
                for (int i = 0; i < list.size(); i++) {
                    stringJson.append(list.get(i));
                    if (i < list.size() - 1)
                        stringJson.append(",");
                }
                stringJson.append("]");
            } else {
                getJson(f.get(obj));
            }
        }
        f.setAccessible(false);
        return stringJson;
    }

    private static boolean isCollection(Field f) {
        for (Class<?> anInterface : f.getType().getInterfaces()) {
            if (anInterface == Collection.class) {
                return true;
            }
        }
        return false;
    }
}


