require 'test/unit'
require 'word_wrap'

class TestWordWrap < Test::Unit::TestCase

  def test_should_not_alter_a_string_shorter_than_wrap_limit
    result = WordWrap.wrap(5, "abc")
    assert_equal ["abc"], result
  end

  def test_should_wrap_on_word_boundries_if_possible
    result = WordWrap.wrap(3, "abc def")
    assert_equal ["abc", "def"], result
  end

  def test_should_wrap_in_middle_of_words
    result = WordWrap.wrap(3, "abcdef")
    assert_equal ["abc", "def"], result
  end

  def test_should_split_across_multiple_lines
    result = WordWrap.wrap(3, "abcdef hij")
    assert_equal ["abc", "def", "hij"], result
  end

  def test_should_preserve_white_space_between_word_wrapping
    result = WordWrap.wrap(3, "a b c d e f")
    assert_equal ["a b", "c d", "e f"], result
  end
end
