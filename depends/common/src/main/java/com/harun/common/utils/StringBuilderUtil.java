package com.harun.common.utils;

public class StringBuilderUtil {

    private StringBuilderUtil() {
    }

    public static String buildMessage(String... parts) {
        StringBuilder message = new StringBuilder();
        for (String part : parts) {
            message.append(part);
        }
        return message.toString();
    }

    public static String buildMessage(String message, Object... args) {
        StringBuilder result = new StringBuilder();
        int argIndex = 0;

        for (int i = 0; i < message.length(); i++) {
            if (i < message.length() - 1 && message.charAt(i) == '{' && message.charAt(i + 1) == '}') {
                if (argIndex < args.length) {
                    result.append(args[argIndex++]);
                }
                i++;
            } else {
                result.append(message.charAt(i));
            }
        }
        return result.toString();
    }
}
