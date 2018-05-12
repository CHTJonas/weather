require 'sinatra'
require 'sinatra/json'
require 'open-uri'
require 'rss'
require 'nokogiri'

set :port, 8080
set :bind, '127.0.0.1'
set :environment, :production

get '/' do
  content_type :json
  url = 'http://www.cl.cam.ac.uk/research/dtg/weather/rss.xml'
  open(url) do |rss|
    feed = RSS::Parser.parse(rss)
    @html = feed.items.first.description
  end
  data = { }
  page = Nokogiri::HTML(@html)
  page.css('tr').each do |row|
    columns = row.css('td')
    data[columns[0].text] = columns[1].text
  end
  data.to_json
end
