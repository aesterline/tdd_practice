class WordWrap
  def self.wrap(wrap_at, input)
    return [input] if input.length < wrap_at

    results = []

    until input.empty? do
      results << input[0, wrap_at]
      input = (input[wrap_at..-1] || '').strip
    end

    results
  end
end
