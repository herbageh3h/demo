require 'active_support'

module VimColors
    class RubyExample
        CONSTANT = /^[0-9]+regex awesome$/

        attr_reader :colorscheme

        #TODO: Bacon ipsum dolar sit amet
        def initialize(attributes = [])
            @colorscheme = attributes[:colorscheme]
        end

        def self.examples
            # Bacon ipsum dollar sit amet
            ['string', :symbol, true, false, nil, 99.9, 1..2].each do | value |
                puts "it appears that #{value.inspect} is a #{value.class}"
            end

            {:key1 => :value1, :key2 => 'value2'}.each do | key, value |
                puts "the #{key.inspect} key has a value of #{value.inspect}" 
            end

            %w[One Two Threee].each { |number| puts number }
        end
    end
end
