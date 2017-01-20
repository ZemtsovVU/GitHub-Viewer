package com.example.githubviewer.util;

import retrofit2.Response;

public final class GitHubHeaderLinksExtractor {
    private static final String HEADER_LINK_KEY = "Link";
    private static final String HEADER_LINK_ITEM_SEPARATOR = ",";
    private static final String HEADER_LINK_SEGMENT_SEPARATOR = ";";
    private static final String HEADER_LINK_NEXT_KEY = "next";
    private static final String HEADER_LINK_LEFT_BRACE = "<";
    private static final String HEADER_LINK_RIGHT_BRACE = ">";

    public static String extractNext(Response response) {
        String nextLink = null;

        String headerLinksString = response.headers().get(HEADER_LINK_KEY);
        String[] links = headerLinksString.split(HEADER_LINK_ITEM_SEPARATOR);
        for (String link : links) {
            String[] segments = link.split(HEADER_LINK_SEGMENT_SEPARATOR);

            if (segments.length < 2) {
                continue;
            }

            if (!segments[1].contains(HEADER_LINK_NEXT_KEY)) {
                continue;
            }

            nextLink = segments[0].trim();

            if (nextLink.startsWith(HEADER_LINK_LEFT_BRACE)) {
                nextLink = nextLink.substring(1, nextLink.length());
            }

            if (nextLink.endsWith(HEADER_LINK_RIGHT_BRACE)) {
                nextLink = nextLink.substring(0, nextLink.length() - 1);
            }

            break;
        }

        return nextLink;
    }
}
