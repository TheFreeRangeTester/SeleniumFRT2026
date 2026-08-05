package models;

public record NavigationExpectation(
        String label,
        String path,
        String expectedText
) {
}
